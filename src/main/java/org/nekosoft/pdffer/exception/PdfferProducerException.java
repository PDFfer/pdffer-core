package org.nekosoft.pdffer.exception;

/**
 * The top-level exception of the PDFfer Producer exception hierarchy.
 */
public class PdfferProducerException extends PdfferException {
    /**
     * Instantiates a new Pdffer producer exception.
     */
    public PdfferProducerException() {
    }

    /**
     * Instantiates a new Pdffer producer exception.
     *
     * @param message the message
     */
    public PdfferProducerException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Pdffer producer exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public PdfferProducerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Pdffer producer exception.
     *
     * @param cause the cause
     */
    public PdfferProducerException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Pdffer producer exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public PdfferProducerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
