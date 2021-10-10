package org.nekosoft.pdffer.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * A Property POJO that reflects the configuration of the mailer controller.
 */
@ConfigurationProperties(prefix = "pdffer.mailer.controller")
@ConstructorBinding
public class PdfferMailerControllerProps {
    // field declarations
    private final String baseUri;
    private final String mailUri;

    /**
     * Instantiates a new PDFfer mailer controller props.
     *
     * @param baseUri the base URL of the controller
     * @param mailUri the mail URL of the controller
     */
    public PdfferMailerControllerProps(String baseUri, String mailUri) {
        // set field values
        this.baseUri = baseUri;
        this.mailUri = mailUri;
    }

    // getters only

    /**
     * Gets the base URL of the controller.
     *
     * @return the base URL
     */
    public String getBaseUri() {
        return baseUri;
    }

    /**
     * Gets the URL for the mail endpoint of the controller.
     *
     * @return the mail URL
     */
    public String getMailUri() {
        return mailUri;
    }

}
