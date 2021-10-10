package org.nekosoft.pdffer;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The main Spring configuration class for PDFfer Core. It scans for Spring beans from the current package down,
 * and also reads all Properties POJOs from the {@link org.nekosoft.pdffer.props} package.
 */
@Configuration
@ComponentScan
@ConfigurationPropertiesScan(basePackages = "org.nekosoft.pdffer.props")
public class PdfferCoreConfiguration {
    /**
     * The name of the profile that includes the Spring Actuator beans for PDFfer.
     */
    public static final String PROFILE_ACTUATOR = "pddfer-actuator";
    /**
     * The name of the profile that includes the Spring Shell beans for PDFfer.
     */
    public static final String PROFILE_SHELL = "pddfer-shell";
    /**
     * The name of the profile that includes the PDFfer Explorer website.
     */
    public static final String PROFILE_EXPLORER = "pddfer-explorer";
}
