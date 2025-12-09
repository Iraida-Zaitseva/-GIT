package com.example.config;

import org.h2.server.web.WebServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.example.controller", "com.example.service", "com.example.dao"})
public class WebConfig implements WebMvcConfigurer, ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        try {
            onStartup(servletContext);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void onStartup(ServletContext servletContext) throws ServletException {
        // Регистрация H2 console сервлета
        ServletRegistration.Dynamic h2Console = servletContext.addServlet("H2Console", new WebServlet());
        h2Console.addMapping("/h2-console/*");
        h2Console.setInitParameter("webAllowOthers", "true");
        h2Console.setLoadOnStartup(1);
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
