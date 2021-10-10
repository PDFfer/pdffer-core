package org.nekosoft.PDFferTemplates;

import org.nekosoft.pdffer.template.PdfTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.nekosoft.pdffer.registry.TemplateRegistrationTests.BEAN_NO_ANNOTATION;

/*
 * this template should not get registered as it does not include the PDFTemplateComponent annotation
 */
@Component(BEAN_NO_ANNOTATION)
public class TestPdfNoTemplateB<T> implements PdfTemplate<T> {
    @Override
    public Class<T> getPayloadClass() {
        return null;
    }

    @Override
    public T getPayload() {
        return null;
    }

    @Override
    public void setPayload(T payload) {
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void generate() {
    }

    @Override
    public byte[] getPdfContent() {
        return new byte[0];
    }
}
