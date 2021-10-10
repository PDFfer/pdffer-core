package org.nekosoft.pdffer.mail;

import org.nekosoft.pdffer.exception.MailAddressException;
import org.nekosoft.pdffer.exception.MailMessageException;
import org.nekosoft.pdffer.exception.MailSenderException;
import org.nekosoft.pdffer.exception.PdfferMailerException;
import org.nekosoft.pdffer.props.PdfferMailerControllerProps;
import org.nekosoft.pdffer.props.PdfferMailerProps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>The PDFfer mailer bean.</p>
 * <p>This bean is used to send emails with PDF attachments directly from PDFfer. It requires that the mail dependencies are
 * present in the Spring Boot application (the spring-boot-starter-mail artifact) and that the {@code pdffer.mailer.beans.enable}
 * property is set to {@code true}.</p>
 * <p> This class has methods that will take PDF content as an array of bytes and
 * will send it over to the given email address or addresses.</p>
 */
@Component
@ConditionalOnClass(name = "org.springframework.mail.javamail.JavaMailSenderImpl")
@ConditionalOnProperty(name = "pdffer.mailer.beans.enable", havingValue = "true", matchIfMissing = false)
public class PdfferMailerBean {

    private final JavaMailSender mailSender;

    private PdfferMailerProps props;

    /**
     * Allows Spring to create the bean instance and pass the {@code JavaMailSender} and {@link PdfferMailerProps} instances into it.
     *
     * @param mailSender the mail sender
     * @param props      the props
     */
    public PdfferMailerBean(JavaMailSender mailSender, PdfferMailerProps props) {
        this.mailSender = mailSender;
        this.props = props;
    }

    /**
     * Send an email message with a PDF attachment.
     *
     * @param toString   the email addresses to send the email to as a comma-separated string
     * @param subject    the subject of the email
     * @param text       the text of the main message of the email
     * @param attachment the attachment PDF as an array of bytes
     * @param filename   the filename of the attachment
     * @param sendFrom   the send-from email address (can be null)
     * @param replyTo    the reply-to email address (can be null)
     * @throws MailAddressException the {@code MailAddressException} raised if invalid email addresses were found
     * @throws MailMessageException the {@code MailMessageException} raised if any issues were encountered while creating the message body
     * @throws MailSenderException the {@code MailSenderException} raised if any issues were encountered while sending email
     */
    public void sendMessageWithPdfAttachment(String toString, String subject, String text, byte[] attachment, String filename, String sendFrom, String replyTo) throws MailAddressException, MailMessageException, MailSenderException {
        sendMessageWithPdfAttachmentToList(Arrays.asList(toString.split(",")), subject, text, attachment, filename, sendFrom, replyTo);
    }

    /**
     * Send an email message with a PDF attachment.
     *
     * @param toList     the email addresses to send the email to as a list of strings
     * @param subject    the subject of the email
     * @param text       the text of the main message of the email
     * @param attachment the attachment PDF as an array of bytes
     * @param filename   the filename of the attachment
     * @param sendFrom   the send-from email address (can be null)
     * @param replyTo    the reply-to email address (can be null)
     * @throws MailAddressException the {@code MailAddressException} raised if invalid email addresses were found
     * @throws MailMessageException the {@code MailMessageException} raised if any issues were encountered while creating the message body
     * @throws MailSenderException the {@code MailSenderException} raised if any issues were encountered while sending email
     */
    public void sendMessageWithPdfAttachmentToList(List<String> toList, String subject, String text, byte[] attachment, String filename, String sendFrom, String replyTo) throws MailAddressException, MailMessageException, MailSenderException {
        try {
            List<InternetAddress> recipients = new ArrayList<>();
            for (String email : toList) {
                recipients.add(new InternetAddress(email.trim()));
                sendMessageWithPdfAttachment(recipients, subject, text, attachment, filename, sendFrom, replyTo);
            }
        } catch (AddressException e) {
            throw new MailAddressException(e);
        }
    }

    /**
     * Send an email message with a PDF attachment.
     *
     * @param to         the email addresses to send the email to as a {@code java.util.List} of
     *                   {@code javax.mail.internet.InternetAddress}
     * @param subject    the subject of the email
     * @param text       the text of the main message of the email
     * @param attachment the attachment PDF as an array of bytes
     * @param filename   the filename of the attachment
     * @param sendFrom   the send-from email address (can be null)
     * @param replyTo    the reply-to email address (can be null)
     * @throws MailAddressException the {@code MailAddressException} raised if invalid email addresses were found
     * @throws MailMessageException the {@code MailMessageException} raised if any issues were encountered while creating the message body
     * @throws MailSenderException the {@code MailSenderException} raised if any issues were encountered while sending email
     */
    public void sendMessageWithPdfAttachment(List<InternetAddress> to, String subject, String text, byte[] attachment, String filename, String sendFrom, String replyTo) throws MailAddressException, MailMessageException, MailSenderException {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            if (sendFrom != null) {
                helper.setFrom(sendFrom);
            } else {
                helper.setFrom(String.format("\"%s\" <%s>", props.getSendFrom().getName(), props.getSendFrom().getAddress()));
            }
            if (replyTo != null) {
                helper.setReplyTo(replyTo);
            } else {
                helper.setReplyTo(String.format("\"%s\" <%s>", props.getReplyTo().getName(), props.getReplyTo().getAddress()));
            }
            helper.setTo(to.toArray(new InternetAddress[]{}));
            helper.setSubject(subject);
            helper.setText(text);

            helper.addAttachment(filename, new ByteArrayResource(attachment), MediaType.APPLICATION_PDF_VALUE);
        } catch (AddressException e) {
            throw new MailAddressException(e);
        } catch (MessagingException e) {
            throw new MailMessageException(e);
        }

        try {
            mailSender.send(message);
        } catch (MailException e) {
            throw new MailSenderException(e);
        }
    }

}
