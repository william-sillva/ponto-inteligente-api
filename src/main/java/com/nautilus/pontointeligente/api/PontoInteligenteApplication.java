package com.nautilus.pontointeligente.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * FIXME - Removendo Security SpringBoot
 * exclude = {SecurityAutoConfiguration.class}
 * @SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
 */

@SpringBootApplication()
public class PontoInteligenteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PontoInteligenteApplication.class, args);
    }

}
