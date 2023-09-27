package com.akash.employeemanagementsystem.config;

import com.akash.employeemanagementsystem.service.ExternalServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationServiceConfigurations {

    @Bean
    public ExternalServices externalServices(){
        return new ExternalServices();
    }
}
