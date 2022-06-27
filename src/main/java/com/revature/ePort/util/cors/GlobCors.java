package com.revature.ePort.util.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class GlobCors implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/ePort/**")
                .allowedMethods("PUT", "DELETE","GET","POST")
                .allowedHeaders("header1", "header2", "header3","Authorization")
                .exposedHeaders("Authorization")
                .allowCredentials(false).maxAge(3600);
    }
}

