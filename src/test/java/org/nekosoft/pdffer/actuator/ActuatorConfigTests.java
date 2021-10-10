package org.nekosoft.pdffer.actuator;

import org.junit.jupiter.api.Test;
import org.nekosoft.pdffer.PdfferCoreConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.nekosoft.pdffer.PdfferCoreConfiguration.PROFILE_ACTUATOR;

@ActiveProfiles(PROFILE_ACTUATOR)
@ContextConfiguration(classes = PdfferCoreConfiguration.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
public class ActuatorConfigTests {

    @Autowired
    ApplicationContext context;

    @Test
    public void templateRegistyEndpointExists() {
        context.getBean("templateRegistryEndpoint", TemplateRegistryEndpoint.class);
    }

    @Test
    public void webControllerEndpointExists() {
        context.getBean("mailerControllerInfoEndpoint", MailerControllerInfoEndpoint.class);
    }

    @Test
    public void mailerControllerEndpointExists() {
        context.getBean("webControllerInfoEndpoint", WebControllerInfoEndpoint.class);
    }

    @Test
    public void mailerInfoEndpointExists() {
        context.getBean("mailerInfoEndpoint", MailerInfoEndpoint.class);
    }

}