package org.nekosoft.pdffer.registry;

import org.nekosoft.pdffer.template.PdfTemplateComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.nekosoft.pdffer.template.PdfTemplateComponent.BASE_PACKAGE;

/**
 * <p>Older Java Config-based context for the template registry.</p>
 * <p>It was replaced by a different approach that uses a {@link PdfferTemplateComponentScanner custom scanner} class in order to support template groups.</p>
 * <p>This configuration scans the {@link org.nekosoft.PDFferTemplates} package for templates.</p>
 * <p>The scanner is configured with a custom filter, name generator and scope resolver, and does not use
 * the default filters. In order to be picked up, classes need to implement the {@link org.nekosoft.pdffer.template.PdfTemplate PdfTemplate}
 * interface and be annotated with {@link org.nekosoft.pdffer.template.PdfTemplateComponent PdfTemplateComponent}.</p>
 * <p>Also see {@link PdfferTemplateBeanNameGenerator}, {@link PdfferTemplateScopeMetadataResolver}, {@link PdfferTemplateExcludeFilter}.</p>
 */
@Deprecated(forRemoval = false)
//@Configuration
@ComponentScan(
		basePackages = BASE_PACKAGE,
		useDefaultFilters = false,
		excludeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM, value = PdfferTemplateExcludeFilter.class),
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = PdfTemplateComponent.class),
		nameGenerator = PdfferTemplateBeanNameGenerator.class,
		scopeResolver = PdfferTemplateScopeMetadataResolver.class
)
public class PdfferRegistryConfiguration {

}
