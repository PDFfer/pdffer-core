package org.nekosoft.pdffer.mail;

import java.util.Properties;

import org.nekosoft.pdffer.props.PdfferMailerProps;
import org.nekosoft.pdffer.props.SmtpServerInfo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * <p>A conditional Spring configuration class for the email functionality of PDFfer.</p>
 * <p>This configuration is only imported if the mail dependencies are present in the
 * Spring Boot application. The configuration must also be enabled with the {@code pdffer.mailer.beans.enable}
 * property, otherwise it will not be loaded even if the mail dependencies are present.</p>
 */
@Configuration
@ConditionalOnClass(name = "org.springframework.mail.javamail.JavaMailSender")
@ConditionalOnProperty(name = "pdffer.mailer.beans.enable", havingValue = "true", matchIfMissing = false)
public class PdfferMailerConfiguration {

    /**
     * The {@code JavaMailSender} bean that provides details on how to send email from PDFfer.
     *
     * @param javaMailprops the configuration properties relating to the email functionality
     * @return the {@code JavaMailSender} bean
     */
    @Bean
    public JavaMailSender mailSender(PdfferMailerProps javaMailprops) {
        SmtpServerInfo smtpInfo = javaMailprops.getSmtp();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpInfo.getHost());
        mailSender.setPort(smtpInfo.getPort());

        mailSender.setUsername(smtpInfo.getUsername());
        mailSender.setPassword(smtpInfo.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        if (smtpInfo.getJavaMailProperties() != null) props.putAll(smtpInfo.getJavaMailProperties());

        return mailSender;
    }

}
