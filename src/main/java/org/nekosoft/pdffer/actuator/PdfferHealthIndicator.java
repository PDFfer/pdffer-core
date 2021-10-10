package org.nekosoft.pdffer.actuator;

import org.nekosoft.pdffer.PdfferProducerBean;
import org.nekosoft.pdffer.mail.PdfferMailerBean;
import org.nekosoft.pdffer.props.PdfferWebControllerProps;
import org.nekosoft.pdffer.registry.PdfferRegistryBean;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.nekosoft.pdffer.PdfferCoreConfiguration.PROFILE_ACTUATOR;

/**
 * A Spring Actuator Health Indicator to assess the health of the PDFfer system.
 */
@Component
@ConditionalOnClass(name = "org.springframework.boot.actuate.health.HealthIndicator")
@Profile(PROFILE_ACTUATOR)
public class PdfferHealthIndicator implements HealthIndicator {

    private ApplicationContext context;

    /**
     * Allows Spring to create the endpoint instance and pass the {@link ApplicationContext} instance into it.
     *
     * @param context the application context to be injected by the framework
     */
    public PdfferHealthIndicator(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Health health() {
        List<String> missing = new ArrayList<>();
        List<String> tooMany = new ArrayList<>();

        String[] producer = context.getBeanNamesForType(PdfferProducerBean.class);
        if (producer.length == 0) {
            missing.add("producer");
        } else if (producer.length > 1) {
            tooMany.add("producer");
        }

        String[] registry = context.getBeanNamesForType(PdfferRegistryBean.class);
        if (registry.length == 0) {
            missing.add("registry");
        } else if (registry.length > 1) {
            tooMany.add("registry");
        }

        String[] mailer = context.getBeanNamesForType(PdfferMailerBean.class);
        if (mailer.length == 0) {
            missing.add("mailer");
        } else if (mailer.length > 1) {
            tooMany.add("mailer");
        }

        if (missing.size() == 0 && tooMany.size() == 0) {
            return Health.up()
                    .withDetail("producer", producer[0])
                    .withDetail("registry", registry[0])
                    .withDetail("mailer", mailer[0])
                    .build();
        } else {
            Health.Builder healthBuilder;
            if (missing.size() > 0) {
                healthBuilder = Health.down();
            } else {
                healthBuilder = Health.unknown();
            }
            return healthBuilder
                    .withDetail("producer", missing.contains("producer") ? "<none>" : "<many>")
                    .withDetail("registry", missing.contains("registry") ? "<none>" : "<many>")
                    .withDetail("mailer", missing.contains("mailer") ? "<none>" : "<many>")
                    .build();
        }

    }

}