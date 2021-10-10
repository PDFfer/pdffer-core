package org.nekosoft.pdffer.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nekosoft.pdffer.exception.TemplateGroupNotFoundException;
import org.nekosoft.pdffer.exception.TemplateNotFoundException;
import org.nekosoft.pdffer.props.PdfferMailerProps;
import org.nekosoft.pdffer.template.PdfTemplate;
import org.nekosoft.pdffer.template.PdfTemplateComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import static org.nekosoft.pdffer.template.PdfTemplate.getTemplatePath;
import static org.nekosoft.pdffer.template.PdfTemplate.splitTemplatePath;
import static org.nekosoft.pdffer.template.PdfTemplateComponent.*;

/**
 * The PDFfer bean that deals with PDF templates. It scans a specified package for
 * {@link org.nekosoft.pdffer.template.PdfTemplateComponent PdfTemplateComponent}-annotated implementations of
 * {@link org.nekosoft.pdffer.template.PdfTemplate PdfTemplate} and makes the instances available
 * via the {@link #findTemplate(String)} method.
 * <p>
 * It creates its own Spring application context, separate from the main one.
 */
@Component
public class PdfferRegistryBean {

    private static final Logger logger = LoggerFactory.getLogger(PdfferRegistryBean.class);

    private final Map<String, AnnotationConfigApplicationContext> templateRegistries;

    /**
     * Allows Spring to create the bean instance.
     */
    public PdfferRegistryBean() {
        this(BASE_PACKAGE);
    }

    /**
     * This constructor is for testing purposes only.
     *
     * @param basePackage the base package
     */
    PdfferRegistryBean(String basePackage) {
        logger.trace("Creating template registry application context");

        PdfferTemplateComponentScanner scanner = new PdfferTemplateComponentScanner();
        scanner.scan(basePackage);

        templateRegistries = scanner.getTemplateRegistries();
        for (AnnotationConfigApplicationContext context : templateRegistries.values()) {
            context.refresh();
            context.start();
        }

        if (logger.isDebugEnabled()) {
            for (String templateName : listTemplatePaths()) {
                logger.debug("Added PDF template [{}]", templateName);
            }
        }
        logger.trace("Created separate template context [{}]", scanner.getRootRegistry());
    }

    /**
     * Finds a PDF template given the template path.
     *
     * @param templatePath the template path
     * @return the PDF template instance
     * @throws TemplateNotFoundException if a template at the given template path could not be found
     */
    public PdfTemplate<?> findTemplateByPath(String templatePath) throws TemplateNotFoundException {
        logger.trace("Looking for template path [{}]", templatePath);
        String[] groupName = splitTemplatePath(templatePath);
        return findTemplate(groupName[0], groupName[1]);
    }

    /**
     * Finds a PDF template in the root registry given the name.
     *
     * @param templateName the template name in the root registry
     * @return the PDF template from the root registry
     * @throws TemplateNotFoundException if a template with the given name in the root registry could not be found
     */
    public PdfTemplate<?> findTemplate(String templateName) throws TemplateNotFoundException {
        logger.trace("Looking for template name [{}] in ROOT_REGISTRY", templateName);
        return findTemplate(ROOT_REGISTRY, templateName);
    }

    /**
     * Finds a PDF template in the given group with the given name.
     *
     * @param group        the name of the group
     * @param templateName the name of the template
     * @return the PDF template from the given group
     * @throws TemplateNotFoundException if a template with the given name in the given group could not be found
     */
    public PdfTemplate<?> findTemplate(String group, String templateName) throws TemplateNotFoundException {
        logger.debug("Looking for template name [{}] in group [{}]", templateName, group);
        AnnotationConfigApplicationContext context = templateRegistries.get(group.trim());
        if (context == null) {
            throw new TemplateGroupNotFoundException(group);
        }
        try {
            return context.getBean(templateName.trim(), PdfTemplate.class);
        } catch (NoSuchBeanDefinitionException e) {
            throw new TemplateNotFoundException(group, templateName);
        }
    }

    /**
     * Provides a list of all PDF template paths that were registered with this context.
     *
     * @return the list of PDF template paths
     */
    public List<String> listTemplatePaths() {
        AnnotationConfigApplicationContext context = templateRegistries.get(ROOT_REGISTRY);
        List<String> names = new ArrayList<>(List.of(context.getBeanNamesForAnnotation(PdfTemplateComponent.class)));
        for (var subcontextEntry : templateRegistries.entrySet()) {
            if (!ROOT_REGISTRY.equals(subcontextEntry.getKey())) {
                names.addAll(Stream.of(subcontextEntry.getValue().getBeanNamesForAnnotation(PdfTemplateComponent.class))
                        .map(s -> getTemplatePath(subcontextEntry.getKey(), s))
                        .sorted()
                        .collect(Collectors.toList())
                );
            }
        }
        return names;
    }

    /**
     * Provides a list of all PDF template groups that were registered with this context.
     *
     * @return the list of PDF template groups
     */
    public List<String> listGroupNames() {
        return templateRegistries.keySet().stream().map(name -> ROOT_REGISTRY.equals(name) ? GROUP_SEPARATOR : name ).sorted().collect(Collectors.toList());
    }

    /**
     * Provides a list of all PDF templates that were registered in the root registry (the one with the default or no group associated to it).
     *
     * @return the list of all PDF templates in the root registry
     */
    public List<String> listTemplateNamesInRoot() {
        return listTemplateNamesInGroup(ROOT_REGISTRY);
    }

    /**
     * Provides a list of all PDF templates that were registered in the given group.
     *
     * @param group the group name
     * @return the list of templates in the given group
     */
    public List<String> listTemplateNamesInGroup(String group) {
        List<String> names = new ArrayList<>(List.of(templateRegistries.get(group).getBeanNamesForAnnotation(PdfTemplateComponent.class)));
        Collections.sort(names);
        return names;
    }

    /**
     * This method is for testing purposes only.
     *
     * @return the template registries
     */
    Map<String, AnnotationConfigApplicationContext> getTemplateRegistries() {
        return templateRegistries;
    }

}
