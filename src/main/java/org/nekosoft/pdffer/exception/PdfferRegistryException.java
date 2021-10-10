package org.nekosoft.pdffer.exception;

/**
 * The top-level exception of the PDFfer Registry exception hierarchy.
 */
public class PdfferRegistryException extends PdfferException {
    /**
     * Instantiates a new Pdffer registry exception.
     */
    public PdfferRegistryException() {
    }

    /**
     * Instantiates a new Pdffer registry exception.
     *
     * @param message the message
     */
    public PdfferRegistryException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Pdffer registry exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public PdfferRegistryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Pdffer registry exception.
     *
     * @param cause the cause
     */
    public PdfferRegistryException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Pdffer registry exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public PdfferRegistryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
