package org.nekosoft.pdffer.exception;

import javax.mail.MessagingException;

/**
 * An exception raised when the mailer was not able to compose an email message.
 */
public class MailMessageException extends PdfferMailerException {
    /**
     * Creates a new instance.
     *
     * @param cause the reason why the email message could not be composed
     */
    public MailMessageException(MessagingException cause) {
        super(cause);
    }
}
