package org.nekosoft.pdffer.registry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.nekosoft.pdffer.template.PdfTemplateComponent.GROUP_SEPARATOR;

import java.util.List;

public class RegistryListTests {

    @Test
    public void emptyListOfGroupNamesWorks() {
        PdfferRegistryBean registry = new PdfferRegistryBean("org.nekosoft.TestPDFferTemplates.registryempty");
        List<String> groups = registry.listGroupNames();
        assertEquals(groups.size(), 1, "Group list does not contain only one element");
        assertEquals(groups.get(0), GROUP_SEPARATOR, "Only the root group should be in the list");
    }

    @Test
    public void emptyListOfTemplatePathsWorks() {
        PdfferRegistryBean registry = new PdfferRegistryBean("org.nekosoft.TestPDFferTemplates.registryempty");
        List<String> paths = registry.listTemplatePaths();
        assertTrue(paths.isEmpty(), "Template path list is not empty");
    }

    @Test
    public void emptyListOfTemplateNamesWorks() {
        PdfferRegistryBean registry = new PdfferRegistryBean("org.nekosoft.TestPDFferTemplates.registryempty");
        List<String> names = registry.listTemplateNamesInRoot();
        assertTrue(names.isEmpty(), "Template name list is not empty");
    }

    @Test
    public void listOfTemplateNamesInGroup() {
        PdfferRegistryBean registry = new PdfferRegistryBean("org.nekosoft.TestPDFferTemplates.registrylists1");
        List<String> names = registry.listTemplateNamesInGroup("Group1");
        assertEquals(names.size(), 3, "Template name list should have 3 elements");
    }

}
