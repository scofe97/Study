package com.example.demo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.TypedQuery;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RedisSampleController {

    private final RedisSampleService redisSampleService;

    @CrossOrigin(origins="*")
    @GetMapping (value = "redis/{id}")
    @Cacheable(value = "DataDto", key = "#id", cacheManager = "cacheManager")
    public List<DataDto> getRedis(@PathVariable Long id) {
        List<DataDto> result = redisSampleService.getRedisStringValue(id);
        return result;
    }

    @CrossOrigin(origins="*")
    @GetMapping (value = "normal/{id}")
    public List<DataDto> getH2(@PathVariable Long id) {
        List<DataDto> result = redisSampleService.getRedisStringValue(id);
        return result;
    }
}
