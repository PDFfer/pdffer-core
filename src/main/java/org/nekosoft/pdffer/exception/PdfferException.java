package org.nekosoft.pdffer.exception;

/**
 * The top-level exception of the PDFfer exception hierarchy.
 */
public class PdfferException extends RuntimeException {
    /**
     * Instantiates a new Pdffer exception.
     */
    public PdfferException() {
    }

    /**
     * Instantiates a new Pdffer exception.
     *
     * @param message the message
     */
    public PdfferException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Pdffer exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public PdfferException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Pdffer exception.
     *
     * @param cause the cause
     */
    public PdfferException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Pdffer exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public PdfferException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
