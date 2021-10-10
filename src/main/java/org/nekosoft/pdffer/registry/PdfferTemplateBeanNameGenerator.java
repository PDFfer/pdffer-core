package org.nekosoft.pdffer.registry;

import org.nekosoft.pdffer.template.PdfTemplateComponent;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

import static org.nekosoft.pdffer.template.PdfTemplate.getTemplatePath;

/**
 * Determines the name of a PDF template bean on the basis of the
 * {@link PdfTemplateComponent#name() name} attribute of the  {@link PdfTemplateComponent} annotation.
 */
public class PdfferTemplateBeanNameGenerator implements BeanNameGenerator {
	
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		try {
			Class<?> cls = Class.forName(definition.getBeanClassName());
			PdfTemplateComponent annotation = cls.getAnnotation(PdfTemplateComponent.class);
			return annotation.name();
		} catch (ClassNotFoundException e) {
			// this should never happen, given the scanner filters in place...
			throw new BeanCreationException(definition.getBeanClassName(), "Cannot find bean class");
		}
	}
	
}