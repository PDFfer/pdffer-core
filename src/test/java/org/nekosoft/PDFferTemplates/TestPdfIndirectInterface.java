package org.nekosoft.PDFferTemplates;

import org.nekosoft.pdffer.template.PdfTemplate;
import org.nekosoft.pdffer.template.PdfTemplateComponent;

@PdfTemplateComponent(name = "indirect")
public class TestPdfIndirectInterface extends AbstractPdfTemplate<Object> {
}

class AbstractPdfTemplate<T> implements PdfTemplate<T> {
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