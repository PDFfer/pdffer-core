package org.nekosoft.pdffer.exception;

/**
 * An exception raised when the registry was not able to find the requested template.
 */
public class TemplateNotFoundException extends PdfferRegistryException {

    private final String group;
    private final String templateName;

    /**
     * Creates a new instance.
     *
     * @param group        the group name
     * @param templateName the template name that could not be found
     */
    public TemplateNotFoundException(String group, String templateName) {
        this.group = group;
        this.templateName = templateName;
    }

    /**
     * Gets the group name for the template that could not be found.
     *
     * @return the group name
     */
    public String getGroup() {
        return group;
    }

    /**
     * Gets the template name that could not be found.
     *
     * @return the template name that could not be found
     */
    public String getTemplateName() {
        return templateName;
    }

}
