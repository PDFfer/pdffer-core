package org.nekosoft.pdffer.web;

import org.nekosoft.pdffer.PdfferProducerBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Pdffer controller.
 */
@RestController
@ConditionalOnWebApplication
@ConditionalOnBean(type = "org.nekosoft.pdffer.PdfferProducerBean")
@ConditionalOnProperty(name = "pdffer.web.controller.enable", havingValue = "true", matchIfMissing = false)
@RequestMapping("${pdffer.web.controller.base_uri:pdffer}")
public class PdfferController {

    private static Logger logger = LoggerFactory.getLogger(PdfferController.class);

    private final PdfferProducerBean pdfferProducer;

    /**
     * Instantiates a new Pdffer controller.
     *
     * @param pdfferProducer the pdffer producer
     */
    public PdfferController(PdfferProducerBean pdfferProducer) {
        this.pdfferProducer = pdfferProducer;
    }

    /**
     * Download response entity.
     *
     * @param templateId  the template id
     * @param requestData the request data
     * @return the response entity
     */
    @PostMapping("${pdffer.web.controller.download_uri:download}/{templateId}")
    public ResponseEntity<byte[]> download(@PathVariable String templateId, @RequestBody DownloadRequestData requestData) {
        byte[] pdfBytes;
        try {
            pdfBytes = pdfferProducer.generatePdfDocument(templateId, requestData.getPayload());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
            headers.add(HttpHeaders.PRAGMA, "no-cache");
            headers.add(HttpHeaders.EXPIRES, "0");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + requestData.getFilename());
            return ResponseEntity.ok().headers(headers).contentLength(pdfBytes.length).contentType(MediaType.APPLICATION_OCTET_STREAM).body(pdfBytes);
        } catch (RuntimeException e) {
            logger.debug("Bad Request", e);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Save.
     *
     * @param templateId the template id
     */
    @PostMapping("${pdffer.web.controller.save_uri:save}/{templateId}")
    public void save(@PathVariable String templateId) {

    }
}
