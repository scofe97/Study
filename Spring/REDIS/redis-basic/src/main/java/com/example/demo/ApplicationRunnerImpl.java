package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    private final RedisSampleService redisSampleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        redisSampleService.save();
    }
}
