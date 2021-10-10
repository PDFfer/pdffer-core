package org.nekosoft.pdffer.TestPDFferTemplates.producer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nekosoft.pdffer.PdfferCoreConfiguration;
import org.nekosoft.pdffer.PdfferProducerBean;
import org.nekosoft.pdffer.exception.InvalidPayloadClassException;
import org.nekosoft.pdffer.exception.InvalidPayloadException;
import org.nekosoft.pdffer.exception.InvalidTemplateClassException;
import org.nekosoft.pdffer.registry.PdfferRegistryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.nekosoft.pdffer.template.PdfTemplateComponent.ROOT_REGISTRY;

@ContextConfiguration(classes = PdfferCoreConfiguration.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
public class PdfProducerBeanTests {
    @Autowired
    PdfferProducerBean pdffer;
    @MockBean
    private PdfferRegistryBean registryBean;

    @Test
    public void testPlainMethodOk() {
        when(registryBean.findTemplate(ROOT_REGISTRY, PdfJacksonTemplate.TEMPLATE_NAME)).then(invocation -> new PdfJacksonTemplate());
        PayloadClass payload = new PayloadClass("Test", 12, true);
        byte[] content = pdffer.generatePdfDocument(ROOT_REGISTRY, PdfJacksonTemplate.TEMPLATE_NAME, payload);
        assertEquals(payload.toString(), new String(content, StandardCharsets.UTF_8));
    }

    @Test
    public void testPlainMethodInvalidPayloadClass() {
        when(registryBean.findTemplate(ROOT_REGISTRY, PdfJacksonTemplate.TEMPLATE_NAME)).then(invocation -> new PdfJacksonTemplate());
        Object payload = new Object();
        assertThrows(InvalidPayloadClassException.class, () ->
                pdffer.generatePdfDocument(ROOT_REGISTRY, PdfJacksonTemplate.TEMPLATE_NAME, payload)
        );
    }

    @Test
    public void testPlainMethodInvalidPayload() {
        when(registryBean.findTemplate(ROOT_REGISTRY, PdfJacksonTemplate.TEMPLATE_NAME)).then(invocation -> new PdfJacksonTemplate());
        PayloadClass payload = new PayloadClass("Test", 12, false);
        assertThrows(InvalidPayloadException.class, () ->
                pdffer.generatePdfDocument(ROOT_REGISTRY, PdfJacksonTemplate.TEMPLATE_NAME, payload)
        );
    }

    @Test
    public void testJsonMethodInvalidTemplateClass() {
        when(registryBean.findTemplate(ROOT_REGISTRY, PdfBasicTemplate.TEMPLATE_NAME)).then(invocation -> new PdfBasicTemplate());
        assertThrows(InvalidTemplateClassException.class, () ->
                pdffer.generatePdfDocumentFromJsonString(ROOT_REGISTRY, PdfBasicTemplate.TEMPLATE_NAME, "{\"name\":\"John\",\"value\":13,\"valid\":true}")
        );
    }

    @Test
    public void testMapMethodInvalidTemplate() {
        when(registryBean.findTemplate(ROOT_REGISTRY, PdfBasicTemplate.TEMPLATE_NAME)).then(invocation -> new PdfBasicTemplate());
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "John");
        payload.put("value", 12);
        payload.put("valid", true);
        assertThrows(InvalidTemplateClassException.class, () ->
                pdffer.generatePdfDocumentFromJsonMap(ROOT_REGISTRY, PdfBasicTemplate.TEMPLATE_NAME, payload)
        );
    }

    // TODO Complete all cases
}
