package org.nekosoft.TestPDFferTemplates.registrylists1;

import org.nekosoft.pdffer.template.PdfTemplate;
import org.nekosoft.pdffer.template.PdfTemplateComponent;

/*
 * Group1 and Group2 both contain the same bean name and that is ok!
 */
@PdfTemplateComponent(group = "Group1", name = "template3")
public class TestPdfTemplateList3<T> implements PdfTemplate<T> {
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
