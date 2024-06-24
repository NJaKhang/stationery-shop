package com.group.sshop.config;


import com.cksource.ckfinder.servlet.CKFinderServlet;
import com.group.sshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;

@Configuration
@EnableJpaAuditing
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${storage.root}")
    private String root;

    /*------------------
          config
    --------------------*/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(root);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/public", "classpath:/static/")
                .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));
        registry.addResourceHandler("/content/**")
                .addResourceLocations(root);
    }









    /*------------------
          bean
    --------------------*/

    @Bean
    public ViewResolver viewResolver() {
        var viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCache(false);
        return viewResolver;
    }

    @Bean
    public ISpringTemplateEngine templateEngine() {
        var springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setEnableSpringELCompiler(true);
        springTemplateEngine.setTemplateResolver(templateResolver());
        springTemplateEngine.addDialect(new SpringSecurityDialect());
        return springTemplateEngine;
    }

    @Bean
    public ITemplateResolver templateResolver() {
        var templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setPrefix("classpath:views/");
        templateResolver.setCacheable(false);
        templateResolver.setTemplateMode("HTML");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public ServletRegistrationBean<CKFinderServlet> ckFinderServlet() {
        ServletRegistrationBean<CKFinderServlet> registration = new ServletRegistrationBean<>(new CKFinderServlet());
        registration.addUrlMappings("/ckfinder/*");
        registration.setInitParameters(Collections.singletonMap("basePath", "path/to/your/uploads"));
        return registration;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("vi", "VN"));
        return slr;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:/i18n/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }




}
