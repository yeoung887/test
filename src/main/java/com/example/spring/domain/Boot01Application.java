package com.example.spring.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Boot01Application {

    public static void main(String[] args){
        SpringApplication.run(Boot01Application.class, args);
    }
}
