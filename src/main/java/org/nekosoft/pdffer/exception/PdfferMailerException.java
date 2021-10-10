package org.nekosoft.pdffer.exception;

/**
 * The top-level exception of the PDFfer Mailer exception hierarchy.
 */
public class PdfferMailerException extends PdfferException {
    /**
     * Instantiates a new Pdffer mailer exception.
     */
    public PdfferMailerException() {
    }

    /**
     * Instantiates a new Pdffer mailer exception.
     *
     * @param message the message
     */
    public PdfferMailerException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Pdffer mailer exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public PdfferMailerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Pdffer mailer exception.
     *
     * @param cause the cause
     */
    public PdfferMailerException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Pdffer mailer exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public PdfferMailerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
