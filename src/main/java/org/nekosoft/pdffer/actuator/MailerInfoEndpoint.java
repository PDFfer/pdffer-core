package org.nekosoft.pdffer.actuator;

import org.nekosoft.pdffer.props.PdfferMailerProps;
import org.nekosoft.pdffer.props.PdfferWebControllerProps;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static org.nekosoft.pdffer.PdfferCoreConfiguration.PROFILE_ACTUATOR;

/**
 * A Spring Actuator endpoint that allows inspection of the PDFfer mailer configuration.
 */
@Component
@Endpoint(id="pdffer-mailer-props")
@ConditionalOnClass(name = "org.springframework.boot.actuate.endpoint.annotation.Endpoint")
@Profile(PROFILE_ACTUATOR)
public class MailerInfoEndpoint {
	private PdfferMailerProps mailerProps;

	/**
	 * Allows Spring to create the endpoint instance and pass the {@link PdfferMailerProps} instance into it.
	 *
	 * @param mailerProps the {@link PdfferMailerProps} instance to be injected by the Spring framework
	 */
	public MailerInfoEndpoint(PdfferMailerProps mailerProps) {
		this.mailerProps = mailerProps;
	}

	/**
	 * A READ operation that returns the PDFfer mailer configuration in the form of a Properties POJO.
	 *
	 * @return the {@link PdfferMailerProps} instance
	 */
	@ReadOperation
    public PdfferMailerProps props() {
        return mailerProps;
    }
	
}
