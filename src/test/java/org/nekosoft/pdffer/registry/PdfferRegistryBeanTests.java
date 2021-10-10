package org.nekosoft.pdffer.registry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.BeanDefinitionOverrideException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PdfferRegistryBeanTests {

    @Test
    public void beanOverridingNotAllowedInRoot() {
        assertThrows(BeanDefinitionOverrideException.class, () -> {
            new PdfferRegistryBean("org.nekosoft.TestPDFferTemplates.overrides1");
        });
    }

    @Test
    public void beanOverridingNotAllowedInGroups() {
        assertThrows(BeanDefinitionOverrideException.class, () -> {
            new PdfferRegistryBean("org.nekosoft.TestPDFferTemplates.overrides2");
        });
    }

    @Test
    public void beanOverridingOkInSubcontext() {
        new PdfferRegistryBean("org.nekosoft.TestPDFferTemplates.overrides3");
    }

    @Test
    public void beanOverloadsOkAcrossGroups() {
        new PdfferRegistryBean("org.nekosoft.TestPDFferTemplates.overrides4");
    }


}
