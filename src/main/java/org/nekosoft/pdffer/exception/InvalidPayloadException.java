package org.nekosoft.pdffer.exception;

/**
 * An exception raised when the producer was given a payload that does not pass template validation.
 */
public class InvalidPayloadException extends PdfferProducerException {
    private final Object payload;

    /**
     * Creates a new instance.
     *
     * @param payload the payload that fails validation
     */
    public InvalidPayloadException(Object payload) {
        this.payload = payload;
    }

    /**
     * Gets the payload that fails validation.
     *
     * @return the payload that fails validation
     */
    public Object getPayload() {
        return payload;
    }
}
