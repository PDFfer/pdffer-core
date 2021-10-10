package org.nekosoft.pdffer.actuator;

import org.nekosoft.pdffer.props.PdfferWebControllerProps;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static org.nekosoft.pdffer.PdfferCoreConfiguration.PROFILE_ACTUATOR;

/**
 * A Spring Actuator endpoint that allows inspection of the PDFfer web controller configuration.
 */
@Component
@Endpoint(id="pdffer-web-controller-props")
@ConditionalOnClass(name = "org.springframework.boot.actuate.endpoint.annotation.Endpoint")
@Profile(PROFILE_ACTUATOR)
public class WebControllerInfoEndpoint {
	private PdfferWebControllerProps webControllerProps;

	/**
	 * Allows Spring to create the endpoint instance and pass the {@link PdfferWebControllerProps} instance into it.
	 *
	 * @param webControllerProps the {@link PdfferWebControllerProps} instance to be injected by the Spring framework
	 */
	public WebControllerInfoEndpoint(PdfferWebControllerProps webControllerProps) {
		this.webControllerProps = webControllerProps;
	}

	/**
	 * A READ operation that returns the PDFfer web controller configuration in the form of a Properties POJO.
	 *
	 * @return the {@link PdfferWebControllerProps} instance
	 */
	@ReadOperation
    public PdfferWebControllerProps props() {
        return webControllerProps;
    }
	
}
