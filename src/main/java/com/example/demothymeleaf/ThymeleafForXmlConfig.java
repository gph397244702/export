package com.example.demothymeleaf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

/**
 * @author: gph
 * @Description:
 * @date: Created in 2019/7/19 17:06
 * @Modified By:
 */
@Configuration
public class ThymeleafForXmlConfig {


    @Bean()
    SpringResourceTemplateResolver xmlTemplateResolver(ApplicationContext applicationContext) {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".xml");
        templateResolver.setTemplateMode("XML");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);

        return templateResolver;
    }

    @Bean(name = "xmlTemplateEngine")
    SpringTemplateEngine templateEngine(ApplicationContext applicationContext) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(xmlTemplateResolver(applicationContext));
        return templateEngine;
    }
}
