package com.cholnhial.hobbymatewebapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * From my project on Github
 *
 * https://github.com/cholnhial/taca/blob/main/src/main/java/com/cholnhial/taca/config/StaticResourcesWebConfiguration.java
 */
@Configuration
public class StaticResourcesWebConfiguration implements WebMvcConfigurer {

    protected static final String[] RESOURCE_LOCATIONS = new String[] {
            "classpath:/static/"
    };
    protected static final String[] RESOURCE_PATHS = new String[] {
            "/*.js",
            "/*.css",
            "/*.svg",
            "/*.png",
            "*.ico",
            "/content/**",
            "/i18n/*",
    };

    /***
     *
     * With help from JHipster configure static resource handler
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration resourceHandlerRegistration = appendResourceHandler(registry);
        initializeResourceHandler(resourceHandlerRegistration);
    }

    protected ResourceHandlerRegistration appendResourceHandler(ResourceHandlerRegistry registry) {
        return registry.addResourceHandler(RESOURCE_PATHS);
    }

    protected void initializeResourceHandler(ResourceHandlerRegistration resourceHandlerRegistration) {
        resourceHandlerRegistration.addResourceLocations(RESOURCE_LOCATIONS);
    }

}