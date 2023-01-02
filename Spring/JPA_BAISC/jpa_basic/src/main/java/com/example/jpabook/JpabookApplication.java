package com.example.jpabook;

import com.example.jpabook.jpashop.mainJpa.Casccade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;


@Slf4j
@SpringBootApplication
public class JpabookApplication {

    @Bean
    public JpaTest testDataInit(EntityManager em) {
        return new Casccade(em);
    }

    public static void main(String[] args) {
        SpringApplication.run(JpabookApplication.class, args);
    }

}
