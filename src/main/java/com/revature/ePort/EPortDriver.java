package com.revature.ePort;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@ComponentScan("com.revature.ePort")
public class EPortDriver {

    public static void main(String[] args) {
        SpringApplication.run(EPortDriver.class, args);
    }

    /*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/ePort/**")
                        .allowedOrigins(null)
                        .allowedMethods("PUT","DELETE","GET","POST","OPTIONS")
                        .allowedHeaders("header1", "header2", "header3","Authorization")
                        .exposedHeaders("header1", "header2", "Authorization")
                        .allowCredentials(true).maxAge(3600);
            }
        };
    }*/
}
