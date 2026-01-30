package com.bank.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Root application context (non-web beans, e.g., services, repositories)
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MailConfig.class}; // services, dao beans, mail config, etc.
    }

    // Servlet application context (web beans: controllers, view resolver)
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{BankConfig.class}; // BankConfig has @EnableWebMvc and view resolver
    }

    // Map DispatcherServlet to handle all requests
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // Use "/" to map all requests, not "/bank/*"
    }
}
