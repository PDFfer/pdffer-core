package org.nekosoft.pdffer.exception;

import org.nekosoft.pdffer.template.PdfTemplate;

/**
 * <p>An exception raised when the producer is given a template that is not the expected class.</p>
 * <p>This is currently a problem when using any of the JSON or Java map methods in the producer
 * but the template is not a subclass of {@link org.nekosoft.pdffer.template.AbstractJacksonPdfTemplate AbstractJacksonPdfTemplate}.</p>
 */
public class InvalidTemplateClassException extends PdfferProducerException {
    private final Class<? extends PdfTemplate> actualTemplateClass;
    private final Class<? extends PdfTemplate> expectedTemplateClass;

    /**
     * Creates a new instance.
     *
     * @param actualTemplateClass   the class of the template being used
     * @param expectedTemplateClass the expected superclass of templates that can be used
     */
    public InvalidTemplateClassException(Class<? extends PdfTemplate> actualTemplateClass, Class<? extends PdfTemplate> expectedTemplateClass) {
        this.actualTemplateClass = actualTemplateClass;
        this.expectedTemplateClass = expectedTemplateClass;
    }

    /**
     * Gets the actual class of the template being used.
     *
     * @return the actual template class
     */
    public Class<? extends PdfTemplate> getActualTemplateClass() {
        return actualTemplateClass;
    }

    /**
     * Gets the expected superclass of templates that can be used.
     *
     * @return the expected template superclass
     */
    public Class<? extends PdfTemplate> getExpectedTemplateClass() {
        return expectedTemplateClass;
    }
}
