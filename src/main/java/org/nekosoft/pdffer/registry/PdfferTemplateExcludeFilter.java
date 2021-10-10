package org.nekosoft.pdffer.registry;

import org.nekosoft.pdffer.template.PdfTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * A component scanner filter that matches all classes that do not implement the {@link PdfTemplate} interface.
 */
public class PdfferTemplateExcludeFilter implements TypeFilter {
	private static final Logger logger = LoggerFactory.getLogger(PdfferRegistryBean.class);

	@Override
	public boolean match(MetadataReader reader, MetadataReaderFactory factory) {
		try {
			String className = reader.getClassMetadata().getClassName();
			Class<?> c = Class.forName(className);
			boolean isExcluded = !PdfTemplate.class.isAssignableFrom(c);
			logger.debug("Class {} is {}a valid PDF Template implementation", className, isExcluded ? "NOT " : "");
			return isExcluded;
		} catch (ClassNotFoundException e) {
			logger.error("PDF Template class could not be loaded: " + e.getMessage());
			return false;
		}
	}

}