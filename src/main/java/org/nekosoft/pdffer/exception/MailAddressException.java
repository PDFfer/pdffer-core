package org.nekosoft.pdffer.exception;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * An exception raised when the mailer was not able to parse an email address.
 */
public class MailAddressException extends PdfferMailerException {
    /**
     * Creates a new instance.
     *
     * @param cause the reason why the email address could not be parsed
     */
    public MailAddressException(AddressException cause) {
        super(cause);
    }
}
