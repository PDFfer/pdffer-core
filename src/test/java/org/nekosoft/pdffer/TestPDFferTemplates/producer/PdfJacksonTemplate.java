package org.nekosoft.pdffer.TestPDFferTemplates.producer;

import org.nekosoft.pdffer.template.AbstractJacksonPdfTemplate;
import org.nekosoft.pdffer.template.PdfTemplateComponent;

import java.nio.charset.StandardCharsets;

import static org.nekosoft.pdffer.TestPDFferTemplates.producer.PdfJacksonTemplate.TEMPLATE_NAME;

@PdfTemplateComponent(name = TEMPLATE_NAME)
public class PdfJacksonTemplate extends AbstractJacksonPdfTemplate<PayloadClass> {
    static final String TEMPLATE_NAME = "template-jackson";

    @Override
    public Class<PayloadClass> getPayloadClass() {
        return PayloadClass.class;
    }

    @Override
    public void generate() {
        pdfContent = getPayload().toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public boolean validate() {
        return getPayload().isValid();
    }
}
