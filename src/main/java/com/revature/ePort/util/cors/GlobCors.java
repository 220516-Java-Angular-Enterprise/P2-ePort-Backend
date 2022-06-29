package com.revature.ePort.util.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class GlobCors implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("ePort/**")
                .allowedOrigins("*")//"http://localhost:4200/"
                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS", "PATCH")
                .allowedHeaders("header1", "header2", "header3","Authorization","content-type","access-control-allow-origin")
                .exposedHeaders("header1", "header2", "Authorization", "content-type", "access-control-allow-origin");
                //.allowCredentials(false).maxAge(3600);
    }
}

