package org.nekosoft.pdffer.registry;

import org.nekosoft.pdffer.template.PdfTemplateComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.HashMap;
import java.util.Map;

import static org.nekosoft.pdffer.template.PdfTemplate.*;
import static org.nekosoft.pdffer.template.PdfTemplateComponent.ROOT_REGISTRY;

/**
 * <p>A custom scanner that looks for PDF templates for the PDFfer registry.</p>
 * <p>It scans the {@link org.nekosoft.PDFferTemplates} package for templates.</p>
 * <p>The scanner is configured with a custom filter, name generator and scope resolver, and does not use
 * the default filters. In order to be picked up, classes need to implement the {@link org.nekosoft.pdffer.template.PdfTemplate PdfTemplate}
 * interface and be annotated with {@link org.nekosoft.pdffer.template.PdfTemplateComponent PdfTemplateComponent}.</p>
 * <p>Also see {@link PdfferTemplateBeanNameGenerator}, {@link PdfferTemplateScopeMetadataResolver}, {@link PdfferTemplateExcludeFilter}.</p>
 */
public class PdfferTemplateComponentScanner extends ClassPathBeanDefinitionScanner {
    private static final Logger logger = LoggerFactory.getLogger(PdfferTemplateComponentScanner.class);

    private final AnnotationConfigApplicationContext rootContext;
    private final Map<String, AnnotationConfigApplicationContext> templateRegistries;

    /**
     * Instantiates the component scanner and a new application context for the registry.
     */
    public PdfferTemplateComponentScanner() {
        // need to do it this way because I cannot have a constructor create and init the context before a call to super
        this(new AnnotationConfigApplicationContext());
    }

    private PdfferTemplateComponentScanner(AnnotationConfigApplicationContext context) {
        super(context, false);

        rootContext = context;
        rootContext.setId("pdffer-templates");
        rootContext.setAllowBeanDefinitionOverriding(false);

        templateRegistries = new HashMap<>();
        logger.debug("Creating ROOT template group {}", rootContext);
        templateRegistries.put(ROOT_REGISTRY, rootContext);

        addIncludeFilter(new AnnotationTypeFilter(PdfTemplateComponent.class));

        addExcludeFilter(new PdfferTemplateExcludeFilter());

        setBeanNameGenerator(new PdfferTemplateBeanNameGenerator());

        setScopeMetadataResolver(new PdfferTemplateScopeMetadataResolver());

    }

    @Override
    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
        // Might need better insight maybe, but given the constraints of this scanner, we can assume that it is always ok
        // to register beans with the same name so long as they go in different contexts - if they don't the context will
        // fail registration anyway...
        return true;
    }

    @Override
    protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
        try {
            Class<?> cls = Class.forName(definitionHolder.getBeanDefinition().getBeanClassName());
            PdfTemplateComponent annotation = cls.getAnnotation(PdfTemplateComponent.class);
            String templateGroup = annotation.group().trim();
            AnnotationConfigApplicationContext subcontext = templateRegistries.get(templateGroup);
            if (subcontext == null) {
                subcontext = new AnnotationConfigApplicationContext();
                subcontext.setId(templateGroup);
                subcontext.setParent(rootContext);
                subcontext.setAllowBeanDefinitionOverriding(false);
                logger.debug("Creating new template group {}", templateGroup);
                templateRegistries.put(templateGroup, subcontext);
            }
            logger.debug("Registering new template {}", getTemplatePath(templateGroup, definitionHolder.getBeanName()));
            super.registerBeanDefinition(definitionHolder, subcontext);
        } catch (ClassNotFoundException e) {
            // this should never happen, given the scanner filters in place...
            throw new BeanCreationException(definitionHolder.getBeanDefinition().getBeanClassName(), "Cannot find bean class");
        }
    }

    //
    // Methods for testing and internal operations - package visibility only
    //

    /**
     * Provides access to all the template registry contexts that were created (one per group) as a key-value pair, with the
     * key being the name of the corresponding group.
     *
     * @return the template registry contexts
     */
    Map<String, AnnotationConfigApplicationContext> getTemplateRegistries() {
        return templateRegistries;
    }

    /**
     * Gets a template registry context given the name of the associated group. Will return {@code null} if the group does not
     * exist.
     *
     * @param group the group name
     * @return the template registry context, or {@code null}
     */
    AnnotationConfigApplicationContext getTemplateRegistry(String group) {
        return templateRegistries.get(group);
    }

    /**
     * Gets the top-level template registry context, also known as the root registry.
     *
     * @return the root registry
     */
    AnnotationConfigApplicationContext getRootRegistry() {
        return getTemplateRegistry(ROOT_REGISTRY);
    }

}
