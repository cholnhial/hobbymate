package com.cholnhial.hobbymatewebapp.config;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

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

    @Bean
    ErrorViewResolver redirectToFrontEndOn404() {
        return ( request, status, model ) -> status == HttpStatus.NOT_FOUND
                ? new ModelAndView("forward:/index.html", Collections.<String, Object>emptyMap(), HttpStatus.OK)
                : null;
    }

}