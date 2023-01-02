package com.example.demo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RedisSampleService {

    private final EntityManager em;

    public List<DataDto> getRedisStringValue(Long id) {
        return em.createQuery("select d From DataDto as d", DataDto.class).getResultList();
    }
    public List<DataDto> getStringValue(Long id) {
        return em.createQuery("select d From DataDto as d", DataDto.class).getResultList();
    }

    public void save(){
        for (int i = 1; i <= 10000; i++) {
            DataDto dataDto = new DataDto();
            dataDto.setTitle("name " + i);
            em.persist(dataDto);
        }
    }
}
