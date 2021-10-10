package org.nekosoft.pdffer.web;

import org.nekosoft.pdffer.PdfferProducerBean;
import org.nekosoft.pdffer.exception.MailAddressException;
import org.nekosoft.pdffer.exception.MailSenderException;
import org.nekosoft.pdffer.mail.PdfferMailerBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * The type Pdffer mailer controller.
 */
@RestController
@ConditionalOnWebApplication
@ConditionalOnClass(name = "javax.mail.internet.InternetAddress")
@ConditionalOnBean(type = {"org.nekosoft.pdffer.mail.PdfferMailerBean", "org.nekosoft.pdffer.PdfferProducerBean"})
@ConditionalOnProperty(name = "pdffer.mailer.controller.enable", havingValue = "true", matchIfMissing = false)
@RequestMapping("${pdffer.mailer.controller.base_uri:${pdffer.web.controller.base_uri:pdffer}}")
public class PdfferMailerController {

    private static Logger logger = LoggerFactory.getLogger(PdfferMailerController.class);

    private final PdfferMailerBean pdfferMailer;
    private final PdfferProducerBean pdfferProducer;

    /**
     * Instantiates a new Pdffer mailer controller.
     *
     * @param pdfferProducer the pdffer producer
     * @param pdfferMailer   the pdffer mailer
     */
    public PdfferMailerController(PdfferProducerBean pdfferProducer, PdfferMailerBean pdfferMailer) {
        this.pdfferProducer = pdfferProducer;
        this.pdfferMailer = pdfferMailer;
    }

    /**
     * Email.
     *
     * @param templateId  the template id
     * @param requestData the request data
     * @param response    the response
     */
    @PostMapping("${pdffer.web.controller.email_uri:mail}/{templateId}")
    public void email(@PathVariable String templateId, @RequestBody EmailRequestData requestData, HttpServletResponse response) {
        try {
            byte[] pdfBytes = pdfferProducer.generatePdfDocument(templateId, requestData.getPayload());
            pdfferMailer.sendMessageWithPdfAttachmentToList(requestData.getEmailTo(), requestData.getSubject(), requestData.getMessage(), pdfBytes, requestData.getFilename(), requestData.getSendFrom(), requestData.getReplyTo());
        } catch (MailAddressException e) {
            logger.debug("Bad Request", e.getCause());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (MailSenderException e) {
            logger.error("Internal Server Error", e.getCause());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
