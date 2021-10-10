package org.nekosoft.pdffer.actuator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nekosoft.pdffer.props.PdfferWebControllerProps;
import org.nekosoft.pdffer.registry.PdfferRegistryBean;
import org.nekosoft.pdffer.template.PdfTemplate;
import org.nekosoft.pdffer.template.PdfTemplateComponent;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static org.nekosoft.pdffer.PdfferCoreConfiguration.PROFILE_ACTUATOR;

/**
 * A Spring Actuator endpoint that allows inspection of the PDFfer registry.
 */
@Component
@Endpoint(id="pdffer-template-registry")
@ConditionalOnClass(name = "org.springframework.boot.actuate.endpoint.annotation.Endpoint")
@Profile(PROFILE_ACTUATOR)
public class TemplateRegistryEndpoint {
	
	private final PdfferRegistryBean registry;

	/**
	 * Allows Spring to create the endpoint instance and pass the {@link PdfferRegistryBean} instance into it.
	 *
	 * @param registry the {@link PdfferRegistryBean} instance to be injected by the Spring framework
	 */
	public TemplateRegistryEndpoint(PdfferRegistryBean registry) {
		this.registry = registry;
	}

	/**
	 * A READ operation that returns a list of all available templates in the registry.
	 *
	 * @return the list of templates in the registry
	 */
	@ReadOperation
    public List<String> allTemplates() {
        return registry.listTemplatePaths();
    }

	/**
	 * A READ operation that returns details about a specific template identified by the given path.
	 *
	 * @param path the path of the template to be inspected
	 * @return a Java map with the details about the template (the keys are "Class Name", "Template Path"
	 * , "Template Group", "Template Name", "Template Scope").
	 */
	@ReadOperation
    public Map<String, Object> template(@Selector String path) {
		PdfTemplate<?> template = registry.findTemplateByPath(path);
		Map<String, Object> data = new HashMap<>();
		data.put("Class Name", template.getClass().getName());
		data.put("Template Path", path);
		PdfTemplateComponent templateAnnotation = template.getClass().getAnnotation(PdfTemplateComponent.class);
		data.put("Template Group", templateAnnotation.group());
		data.put("Template Name", templateAnnotation.name());
		data.put("Template Scope", templateAnnotation.scope());
        return data;
    }

}
