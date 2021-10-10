package org.nekosoft.pdffer.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * A Property POJO that reflects the configuration of the web controller.
 */
@ConfigurationProperties(prefix = "pdffer.web.controller")
@ConstructorBinding
public class PdfferWebControllerProps {
    // field declarations
    /** The base URL for the controller */
    private final String baseUri;
    /** The URL for the Download endpoint of the controller */
    private final String downloadUri;
    /** The URL for the Save endpoint of the controller */
    private final String saveUri;

    /**
     * Instantiates a new PDFfer web controller props.
     *
     * @param baseUri     the base URL of the controller
     * @param downloadUri the download URL of the controller
     * @param saveUri     the save URL of the controller
     */
    public PdfferWebControllerProps(String baseUri, String downloadUri, String saveUri) {
        // set field values
        this.baseUri = baseUri;
        this.downloadUri = downloadUri;
        this.saveUri = saveUri;
    }

    // getters and setters

    /**
     * Gets the base URL for the controller.
     *
     * @return base URL
     */
    public String getBaseUri() {
        return baseUri;
    }

    /**
     * Gets the URL for the download endpoint of the controller.
     *
     * @return the download URL
     */
    public String getDownloadUri() {
        return downloadUri;
    }

    /**
     * Gets the URL for the save endpoint of the controller.
     *
     * @return the save URL
     */
    public String getSaveUri() {
        return saveUri;
    }

}
