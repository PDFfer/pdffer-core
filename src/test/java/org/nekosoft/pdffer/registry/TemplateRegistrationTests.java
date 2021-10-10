package org.nekosoft.pdffer.registry;

/*
https://developer.okta.com/blog/2019/03/28/test-java-spring-boot-junit5
 */

import org.junit.jupiter.api.Test;
import org.nekosoft.pdffer.PdfferCoreConfiguration;
import org.nekosoft.pdffer.exception.TemplateNotFoundException;
import org.nekosoft.pdffer.template.PdfTemplate;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.nekosoft.pdffer.template.PdfTemplateComponent.ROOT_REGISTRY;

@ContextConfiguration(classes = PdfferCoreConfiguration.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
public class TemplateRegistrationTests {
    public static final String BEAN_NO_INTERFACE = "no-interface";
    public static final String BEAN_NO_ANNOTATION = "no-annotation";
    public static final String BEAN_GROUP = "invalid";
    public static final String BEAN_DEFAULT_GROUP = "-default-group";

    @Autowired
    private PdfferRegistryBean registry;

    @Test
    public void invalidTemplateNoInterface() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            registry.getTemplateRegistries().get(ROOT_REGISTRY).getBean(BEAN_NO_INTERFACE);
        });
    }

    @Test
    public void invalidTemplateNoAnnotation() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            registry.getTemplateRegistries().get(ROOT_REGISTRY).getBean(BEAN_NO_ANNOTATION);
        });
    }

    @Test
    public void invalidTemplateNoInterfaceDefaultGroup() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            registry.getTemplateRegistries().get(ROOT_REGISTRY).getBean(BEAN_NO_INTERFACE + BEAN_DEFAULT_GROUP);
        });
    }

    @Test
    public void invalidTemplateNoInterfaceInGroup() {
        assertNull(registry.getTemplateRegistries().get(BEAN_GROUP));
    }


    @Test
    public void defaultGroupExplicitImplicit() {
        // default group is the root context
        registry.findTemplate("template-a");
        // root context explicitly indicated in annotation
        registry.findTemplate("template-a1");
    }

    @Test
    public void anotherGroupWithTwo() {
        registry.findTemplate("subcontext1", "template-b");
        registry.findTemplate("subcontext1", "template-f");
    }

    @Test
    public void anotherGroupWithThree() {
        registry.findTemplate("subcontext2", "template-c");
        registry.findTemplate("subcontext2", "template-d");
        registry.findTemplate("subcontext2", "template-e");
    }

    @Test
    public void anotherGroupWithOne() {
        registry.findTemplate("subcontext3", "template-g");
    }

    @Test
    public void contextInheritanceWorksFromChildUp() {
        PdfTemplate<?> obj1, obj2;
        obj1 = registry.findTemplate("subcontext1", "template-a");
        obj2 = registry.findTemplate(ROOT_REGISTRY, "template-a");
        assertSame(obj1.getClass(), obj2.getClass());
        // same test but using explicit root context name instead of default
        obj1 = registry.findTemplate("template-a1");
        obj2 = registry.findTemplate("subcontext3", "template-a1");
        assertSame(obj1.getClass(), obj2.getClass());
    }

    @Test
    public void contextInheritanceDoesNotWorkFromParentDown() {
        registry.findTemplate("subcontext3", "template-g");
        assertThrows(TemplateNotFoundException.class, () -> {
            registry.findTemplate(ROOT_REGISTRY, "template-g");
        });
    }

    @Test
    public void contextInheritanceOverrides() {
        PdfTemplate<?> obj1, obj2;
        obj1 = registry.findTemplate("Overrides", "overriden-template");
        obj2 = registry.findTemplate(ROOT_REGISTRY, "overriden-template");
        assertNotSame(obj1.getClass(), obj2.getClass());
    }

    @Test
    void sameNameDiffGroupIsOk() {
        PdfTemplate<?> obj1, obj2;
        obj1 = registry.findTemplate("Group1", "same-name");
        obj2 = registry.findTemplate("Group2", "same-name");
        assertNotSame(obj1.getClass(), obj2.getClass());
    }

    @Test
    void subpackageIsPickedUpIntoRoot() {
        registry.findTemplate("subfolder");
    }

    @Test
    void subpackageIsPickedUpIntoGroup() {
        registry.findTemplate("Subfolder", "template");
    }

    @Test
    void implementImplementedIndirectly() {
        registry.findTemplate("indirect");
    }
}
