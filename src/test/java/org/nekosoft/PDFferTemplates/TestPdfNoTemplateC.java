package org.nekosoft.PDFferTemplates;

import org.nekosoft.pdffer.template.PdfTemplateComponent;

import java.util.Map;

import static org.nekosoft.pdffer.registry.TemplateRegistrationTests.BEAN_DEFAULT_GROUP;
import static org.nekosoft.pdffer.registry.TemplateRegistrationTests.BEAN_NO_INTERFACE;
import static org.nekosoft.pdffer.template.PdfTemplateComponent.ROOT_REGISTRY;

/*
 * this template should not get registered as it does not implement the PDFTemplate interface
 */
@PdfTemplateComponent(group = ROOT_REGISTRY, name = BEAN_NO_INTERFACE + BEAN_DEFAULT_GROUP)
public class TestPdfNoTemplateC<T> {

    public Class<T> getPayloadClass() {
        return null;
    }

    public T getPayload() {
        return null;
    }

    public Map<String, Object> getPdfData() {
        return null;
    }

    public boolean validate() {
        return false;
    }

    public void generate() {
    }

    public byte[] getPdfContent() {
        return new byte[0];
    }

    public void setPdfData(Map<String, Object> data) {
    }
}
