package org.nekosoft.pdffer.actuator;

import org.nekosoft.pdffer.props.PdfferMailerControllerProps;
import org.nekosoft.pdffer.props.PdfferWebControllerProps;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static org.nekosoft.pdffer.PdfferCoreConfiguration.PROFILE_ACTUATOR;

/**
 * A Spring Actuator endpoint that allows inspection of the PDFfer mailer controller configuration.
 */
@Component
@Endpoint(id="pdffer-mailer-controller-props")
@ConditionalOnClass(name = "org.springframework.boot.actuate.endpoint.annotation.Endpoint")
@Profile(PROFILE_ACTUATOR)
public class MailerControllerInfoEndpoint {
	private PdfferMailerControllerProps mailerControllerProps;

	/**
	 * Allows Spring to create the endpoint instance and pass the {@link PdfferMailerControllerProps} instance into it.
	 *
	 * @param mailerControllerProps the {@link PdfferMailerControllerProps} instance to be injected by the Spring framework
	 */
	public MailerControllerInfoEndpoint(PdfferMailerControllerProps mailerControllerProps) {
		this.mailerControllerProps = mailerControllerProps;
	}

	/**
	 * A READ operation that returns the PDFfer mailer controller configuration in the form of a Properties POJO.
	 *
	 * @return the {@link PdfferMailerControllerProps} instance
	 */
	@ReadOperation
    public PdfferMailerControllerProps props() {
        return mailerControllerProps;
    }
	
}
