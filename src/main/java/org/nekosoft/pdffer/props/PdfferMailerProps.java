package org.nekosoft.pdffer.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * A Property POJO that reflects the configuration of the mailer bean.
 */
@ConfigurationProperties(prefix = "pdffer.mailer")
@ConstructorBinding
public class PdfferMailerProps {
    // field declarations
    /** The default email address that will be used as the sender for the emails sent by PDFfer */
    private final EmailAddressInfo sendFrom;
    /** The default email address that will be used as the reply-to header for the emails sent by PDFfer */
    private final EmailAddressInfo replyTo;
    /** The settings of the SMTP server that will be used to send email messages from PDFfer */
    private final SmtpServerInfo smtp;

    /**
     * Instantiates a new Pdffer mailer props.
     *
     * @param sendFrom the default email address that will be used as the sender for the emails sent by PDFfer
     * @param replyTo  the default email address that will be used as the reply-to header for the emails sent by PDFfer
     * @param smtp     the settings of the SMTP server that will be used to send email messages from PDFfer
     */
    public PdfferMailerProps(EmailAddressInfo sendFrom, EmailAddressInfo replyTo, SmtpServerInfo smtp) {
        // set field values
        this.sendFrom = sendFrom;
        this.replyTo = replyTo;
        this.smtp = smtp;
    }

    // getters only

    /**
     * Gets the default email address that will be used as the sender for the emails sent by PDFfer.
     *
     * @return the default email address that will be used as the sender
     */
    public EmailAddressInfo getSendFrom() {
        return sendFrom;
    }

    /**
     * Gets the default email address that will be used as the reply-to header for the emails sent by PDFfer.
     *
     * @return the default email address that will be used as the reply-to header
     */
    public EmailAddressInfo getReplyTo() {
        return replyTo;
    }

    /**
     * Gets the settings of the SMTP server that will be used to send email messages from PDFfer.
     *
     * @return the settings of the SMTP server
     */
    public SmtpServerInfo getSmtp() {
        return smtp;
    }

}
