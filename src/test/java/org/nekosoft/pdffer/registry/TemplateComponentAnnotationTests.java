package org.nekosoft.pdffer.registry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nekosoft.pdffer.PdfferCoreConfiguration;
import org.nekosoft.pdffer.template.PdfTemplate;
import org.nekosoft.pdffer.template.PdfTemplateComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = PdfferCoreConfiguration.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
public class TemplateComponentAnnotationTests {
    public static final String BEAN_NAME = "theTemplateName";
    public static final String BEAN_GROUP = "theGroupName";
    public static final String BEAN_SCOPE = "singleton";

    @Autowired
    private PdfferRegistryBean registry;

    private PdfTemplate<?> template;

    @BeforeEach
    public void beforeEachTest() {
        template = registry.findTemplate(BEAN_GROUP, BEAN_NAME);
    }

    @Test
    void verifyGroup() {
        PdfTemplateComponent annotation = template.getClass().getAnnotation(PdfTemplateComponent.class);
        assertEquals(BEAN_GROUP, annotation.group());
    }

    @Test
    void verifyName() {
        PdfTemplateComponent annotation = template.getClass().getAnnotation(PdfTemplateComponent.class);
        assertEquals(BEAN_NAME, annotation.name());
    }

    @Test
    void verifyScope() {
        PdfTemplateComponent annotation = template.getClass().getAnnotation(PdfTemplateComponent.class);
        assertEquals(BEAN_SCOPE, annotation.scope());
    }

}
