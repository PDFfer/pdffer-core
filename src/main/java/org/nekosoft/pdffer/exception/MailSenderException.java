package org.nekosoft.pdffer.exception;

import org.springframework.mail.MailException;

/**
 * An exception raised when the mailer was not able to send an email.
 */
public class MailSenderException extends PdfferMailerException {
    /**
     * Creates a new instance.
     *
     * @param cause the reason why the email could not be sent
     */
    public MailSenderException(MailException cause) {
        super(cause);
    }
}
