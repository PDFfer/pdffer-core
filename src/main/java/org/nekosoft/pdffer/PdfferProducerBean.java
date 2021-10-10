package org.nekosoft.pdffer;

import org.nekosoft.pdffer.exception.InvalidPayloadClassException;
import org.nekosoft.pdffer.exception.InvalidPayloadException;
import org.nekosoft.pdffer.exception.InvalidTemplateClassException;
import org.nekosoft.pdffer.exception.PdfferProducerException;
import org.nekosoft.pdffer.registry.PdfferRegistryBean;
import org.nekosoft.pdffer.template.AbstractJacksonPdfTemplate;
import org.nekosoft.pdffer.template.PdfTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

import static org.nekosoft.pdffer.template.PdfTemplate.splitTemplatePath;
import static org.nekosoft.pdffer.template.PdfTemplateComponent.ROOT_REGISTRY;

/**
 * The main Spring bean in PDFfer Core. This uses PDF templates - that must be provided by separate libraries - in
 * order to generate PDFs on the fly when needed. It needs an instance of {@link PdfferRegistryBean}, that will be
 * used when looking for templates.
 */
@Component
public class PdfferProducerBean {

    private static Method plainPlayloadSetter;
    private static Method jsonPlayloadSetter;
    private static Method mapPlayloadSetter;

    private static final Logger logger = LoggerFactory.getLogger(PdfferProducerBean.class);

    static {
        try {
            plainPlayloadSetter = PdfTemplate.class.getMethod("setPayload", Object.class);
            jsonPlayloadSetter = AbstractJacksonPdfTemplate.class.getMethod("setPayloadJson", String.class);
            mapPlayloadSetter = AbstractJacksonPdfTemplate.class.getMethod("setPayloadMap", Map.class);
        } catch (NoSuchMethodException e) {
            logger.error("FATAL CONDITION IN PRODUCER CREATION", e);
            throw new PdfferProducerException(e);
        }
    }

    private final PdfferRegistryBean registry;

    /**
     * Creates a new PdfferProducerBean with the given registry. This is not usually invoked manually. Spring will
     * create an instance of this class and inject the registry.
     *
     * @param registry the registry
     */
    public PdfferProducerBean(PdfferRegistryBean registry) {
        this.registry = registry;
    }

    /**
     * Generates a new PDF with the template at the given template path and with the given
     * payload. The path is split into group and name using {@code PdfTemplate::splitTemplatePath(String)}.
     *
     * @param templatePath the path (group + separator + name ) of the template to be used
     * @param data         the payload needed by the template for PDF generation
     * @return the bytes of the generated PDF document
     */
    public byte[] generatePdfDocumentByPath(String templatePath, Object data) {
        String[] split = splitTemplatePath(templatePath);
        return generatePdfDocument(split[0], split[1], data);
    }

    /**
     * Generates a new PDF with the given template (that must be present in the root registry) and the given
     * payload.
     *
     * @param templateName the template name (from the root registry)
     * @param data         the payload needed by the template for PDF generation
     * @return the bytes of the generated PDF document
     */
    public byte[] generatePdfDocument(String templateName, Object data) {
        return generatePdfDocument(ROOT_REGISTRY, templateName, data);
    }

    /**
     * Generates a new PDF with a template by the given name and group and with the given
     * payload.
     *
     * @param <T>          the type parameter
     * @param group        the group where the template resides
     * @param templateName the template name
     * @param data         the payload needed by the template for PDF generation
     * @return the bytes of the generated PDF document
     */
    public <T> byte[] generatePdfDocument(String group, String templateName, T data) {
        return innerGeneratePdfDocument(group, templateName, PdfTemplate.class, plainPlayloadSetter, data);
    }

    /**
     * Generates a new PDF with a template at the given path and with the given
     * payload.
     *
     * @param templatePath the path (group + separator + name ) of the template to be used
     * @param data         a string containing a JSON representation of the payload for the PDF
     * @return the bytes of the generated PDF document
     */
    public byte[] generatePdfDocumentByPathFromJsonString(String templatePath, String data) {
        String[] split = splitTemplatePath(templatePath);
        return generatePdfDocumentFromJsonString(split[0], split[1], data);
    }

    /**
     * Generates a new PDF with the given template (that must be present in the root registry) and the given
     * payload.
     *
     * @param templateName the template name (from the root registry)
     * @param data         a string containing a JSON representation of the payload for the PDF
     * @return the bytes of the generated PDF document
     */
    public byte[] generatePdfDocumentFromJsonString(String templateName, String data) {
        return generatePdfDocumentFromJsonString(ROOT_REGISTRY, templateName, data);
    }

    /**
     * Generates a new PDF with a template by the given name and group and with the given
     * payload.
     *
     * @param group        the group where the template resides
     * @param templateName the template name
     * @param data         a string containing a JSON representation of the payload for the PDF
     * @return the bytes of the generated PDF document
     */
    public byte[] generatePdfDocumentFromJsonString(String group, String templateName, String data) {
        return innerGeneratePdfDocument(group, templateName, AbstractJacksonPdfTemplate.class, jsonPlayloadSetter, data);
    }

    /**
     * Generates a new PDF with the template at the given template path and with the given
     * payload. The path is split into group and name using {@code PdfTemplate::splitTemplatePath(String)}.
     *
     * @param templatePath the path (group + separator + name ) of the template to be used
     * @param data         a {@code java.util.Map} representation of the payload for the PDF
     * @return the bytes of the generated PDF document
     */
    public byte[] generatePdfDocumentByPathFromJsonMap(String templatePath, Map<String,Object> data) {
        String[] split = splitTemplatePath(templatePath);
        return generatePdfDocumentFromJsonMap(split[0], split[1], data);
    }

    /**
     * Generates a new PDF with the given template (that must be present in the root registry) and the given
     * payload.
     *
     * @param templateName the template name (from the root registry)
     * @param data         a {@code java.util.Map} representation of the payload for the PDF
     * @return the bytes of the generated PDF document
     */
    public byte[] generatePdfDocumentFromJsonMap(String templateName, Map<String,Object> data) {
        return generatePdfDocumentFromJsonMap(ROOT_REGISTRY, templateName, data);
    }

    /**
     * Generates a new PDF with a template by the given name and group and with the given
     * payload.
     *
     * @param group        the group where the template resides
     * @param templateName the template name
     * @param data         a {@code java.util.Map} representation of the payload for the PDF
     * @return the bytes of the generated PDF document
     */
    public byte[] generatePdfDocumentFromJsonMap(String group, String templateName, Map<String,Object> data) {
        return innerGeneratePdfDocument(group, templateName, AbstractJacksonPdfTemplate.class, mapPlayloadSetter, data);
    }

    private PdfTemplate<?> findTemplate(String group, String template) {
        return registry.findTemplate(group, template);
    }

    private byte[] innerGeneratePdfDocument(String group, String templateName, Class<? extends PdfTemplate> expectedTemplateClass, Method payloadSetter, Object payload) {
        PdfTemplate<?> template = findTemplate(group, templateName);
        Class<? extends PdfTemplate> actualTemplateClass = template.getClass();
        if (!expectedTemplateClass.isAssignableFrom(actualTemplateClass)) {
            logger.error("Class {} is not a valid template for {}", actualTemplateClass, expectedTemplateClass);
            throw new InvalidTemplateClassException(actualTemplateClass, expectedTemplateClass);
        }
        Class<?> expectedPayloadClass = template.getPayloadClass();
        Class<?> actualPayloadClass = payload.getClass();
        if (!expectedPayloadClass.isAssignableFrom(actualPayloadClass)) {
            logger.error("Payload class {} is not a valid payload for {}", actualPayloadClass, expectedPayloadClass);
            throw new InvalidPayloadClassException(actualPayloadClass, expectedPayloadClass);
        }
        try {
            payloadSetter.invoke(template, payload);
        } catch (ReflectiveOperationException e) {
            logger.error("FATAL CONDITION IN PRODUCER INVOCATION", e);
            throw new PdfferProducerException(e);
        }
        if (!template.validate()) {
            throw new InvalidPayloadException(payload);
        }
        template.generate();
        return template.getPdfContent();
    }

}
