package org.nekosoft.PDFferTemplates;

import org.nekosoft.pdffer.template.PdfTemplate;
import org.nekosoft.pdffer.template.PdfTemplateComponent;

import java.util.Map;

import static org.nekosoft.pdffer.registry.TemplateComponentAnnotationTests.*;

/*
 * this template should get registered to the subcontext1 group (which should contain two templates)
 */
@PdfTemplateComponent(group = BEAN_GROUP, name = BEAN_NAME, scope = BEAN_SCOPE)
public class TestPdfTemplateAnnotation<T> implements PdfTemplate<T> {
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
