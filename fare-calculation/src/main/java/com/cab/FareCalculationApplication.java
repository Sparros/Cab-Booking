package com.cab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FareCalculationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FareCalculationApplication.class, args);
    }
    
    @Bean
    public RestTemplate fareCalculationRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
   
}
