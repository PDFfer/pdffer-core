package org.nekosoft.pdffer.actuator;

import org.junit.jupiter.api.Test;
import org.nekosoft.pdffer.PdfferCoreConfiguration;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("")
@ContextConfiguration(classes = PdfferCoreConfiguration.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
public class NoActuatorConfigTests {

    @Autowired
    ApplicationContext context;

    @Test
    public void templateRegistyEndpointDoesNotExist() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            context.getBean("templateRegistryEndpoint", TemplateRegistryEndpoint.class);
        });
    }

    @Test
    public void webControllerEndpointDoesNotExist() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            context.getBean("mailerControllerInfoEndpoint", MailerControllerInfoEndpoint.class);
        });
    }

    @Test
    public void mailerControllerEndpointDoesNotExist() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            context.getBean("webControllerInfoEndpoint", WebControllerInfoEndpoint.class);
        });
    }

    @Test
    public void mailerInfoEndpointDoesNotExist() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            context.getBean("mailerInfoEndpoint", MailerInfoEndpoint.class);
        });
    }

}