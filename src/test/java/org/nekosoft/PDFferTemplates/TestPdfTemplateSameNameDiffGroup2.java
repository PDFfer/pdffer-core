package org.nekosoft.PDFferTemplates;

import org.nekosoft.pdffer.template.PdfTemplate;
import org.nekosoft.pdffer.template.PdfTemplateComponent;

import java.util.Map;

/*
 * Group1 and Group2 both contain the same bean name and that is ok!
 */
@PdfTemplateComponent(group = "Group1", name = "same-name")
public class TestPdfTemplateSameNameDiffGroup2<T> implements PdfTemplate<T> {
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
