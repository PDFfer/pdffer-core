package org.nekosoft.PDFferTemplates.invoice;

import org.nekosoft.pdffer.template.PdfTemplate;
import org.nekosoft.pdffer.template.PdfTemplateComponent;

import java.util.Map;

/*
 * this template should get registered to the default group (which should contain two templates)
 */
@PdfTemplateComponent(group = "Subfolder", name = "template")
public class TestPdfSubTemplateA<T> implements PdfTemplate<T> {
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
