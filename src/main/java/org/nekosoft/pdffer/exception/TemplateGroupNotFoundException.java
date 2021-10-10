package org.nekosoft.pdffer.exception;

/**
 * An exception raised when the registry was not able to find the requested template group.
 */
public class TemplateGroupNotFoundException extends PdfferRegistryException {
    private final String group;

    /**
     * Creates a new instance.
     *
     * @param group the group name that could not be found
     */
    public TemplateGroupNotFoundException(String group) {
        this.group = group;
    }

    /**
     * Gets the group name that could not be found.
     *
     * @return the group name that could not be found
     */
    public String getGroup() {
        return group;
    }
}
