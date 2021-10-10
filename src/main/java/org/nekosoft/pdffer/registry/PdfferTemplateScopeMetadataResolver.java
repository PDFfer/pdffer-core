package org.nekosoft.pdffer.registry;

import org.nekosoft.pdffer.template.PdfTemplateComponent;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;

import java.util.Locale;

import static org.nekosoft.pdffer.template.PdfTemplateComponent.SCOPE_DEFAULT;
import static org.nekosoft.pdffer.template.PdfTemplateComponent.SCOPE_SINGLETON;

/**
 * Determines the scope of a PDF template bean on the basis of the
 * {@link PdfTemplateComponent#scope() scope} attribute of the {@link PdfTemplateComponent} annotation.
 * <p>
 * The value of the attribute is passed on to Spring as the scope of bean. The default is
 * "prototype".
 */
public class PdfferTemplateScopeMetadataResolver implements ScopeMetadataResolver {
	
	@Override
	public ScopeMetadata resolveScopeMetadata(BeanDefinition definition) {
		try {
			Class<?> c = Class.forName(definition.getBeanClassName());
			PdfTemplateComponent annotation = c.getAnnotation(PdfTemplateComponent.class);
			ScopeMetadata scope = new ScopeMetadata();
			String scopeSpec = annotation.scope();
			if (SCOPE_DEFAULT.equalsIgnoreCase(scopeSpec)) {
				scope.setScopeName(BeanDefinition.SCOPE_PROTOTYPE);
			} else if (SCOPE_SINGLETON.equalsIgnoreCase(scopeSpec)) {
				scope.setScopeName(BeanDefinition.SCOPE_SINGLETON);
			} else {
				scope.setScopeName(scopeSpec);
			}
			return scope;
		} catch (ClassNotFoundException e) {
			// this should never happen, given the scanner filters in place...
			throw new BeanCreationException(definition.getBeanClassName(), "Cannot find bean class");
		}
	}

}