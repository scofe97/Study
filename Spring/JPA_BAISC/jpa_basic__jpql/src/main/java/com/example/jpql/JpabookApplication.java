package com.example.jpql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@SpringBootApplication
public class JpabookApplication {

    @Bean
    public JpaTest testDataInit(EntityManager em) {
        return new JpaTest(em);
    }

    public static void main(String[] args) {
        SpringApplication.run(JpabookApplication.class, args);
    }

}
