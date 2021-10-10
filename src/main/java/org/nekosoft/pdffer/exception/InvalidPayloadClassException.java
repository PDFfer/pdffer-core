package org.nekosoft.pdffer.exception;

/**
 * An exception raised when the producer is given a different payload instance to the class expected by the template.
 */
public class InvalidPayloadClassException extends PdfferProducerException {
    private final Class<?> actualPayloadClass;
    private final Class<?> expectedPayloadClass;

    /**
     * Creates a new instance.
     *
     * @param actualPayloadClass   the class of the payload instance the producer was given
     * @param expectedPayloadClass the payload class expected by the PDF template
     */
    public InvalidPayloadClassException(Class<?> actualPayloadClass, Class<?> expectedPayloadClass) {
        this.actualPayloadClass = actualPayloadClass;
        this.expectedPayloadClass = expectedPayloadClass;
    }

    /**
     * Gets the class of the payload instance the producer was given.
     *
     * @return the class of the payload instance
     */
    public Class<?> getActualPayloadClass() {
        return actualPayloadClass;
    }

    /**
     * Gets the payload class expected by the PDF template.
     *
     * @return the class expected by the template
     */
    public Class<?> getExpectedPayloadClass() {
        return expectedPayloadClass;
    }
}
