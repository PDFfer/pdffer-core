package org.nekosoft.pdffer.TestPDFferTemplates.producer;

import org.nekosoft.pdffer.template.PdfTemplate;
import org.nekosoft.pdffer.template.PdfTemplateComponent;

import java.nio.charset.StandardCharsets;

import static org.nekosoft.pdffer.TestPDFferTemplates.producer.PdfBasicTemplate.TEMPLATE_NAME;

@PdfTemplateComponent(name = TEMPLATE_NAME)
public class PdfBasicTemplate implements PdfTemplate<PayloadClass> {
    static final String TEMPLATE_NAME = "template-basic";

    PayloadClass payload;
    byte[] content;

    @Override
    public Class<PayloadClass> getPayloadClass() {
        return PayloadClass.class;
    }

    @Override
    public PayloadClass getPayload() {
        return payload;
    }

    @Override
    public void setPayload(PayloadClass payload) {
        this.payload = payload;
    }

    @Override
    public boolean validate() {
        return getPayload().isValid();
    }

    @Override
    public void generate() {
        content = payload.toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] getPdfContent() {
        return content;
    }
}
