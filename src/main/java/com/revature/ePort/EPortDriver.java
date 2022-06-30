package com.revature.ePort;

import com.revature.ePort.schedule.closer.AuctionCloser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
//@ComponentScan("com.revature.ePort")
public class EPortDriver {

    public static void main(String[] args) {
        SpringApplication.run(EPortDriver.class, args);

    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /*@Bean
    @Scope("singleton")*/


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
