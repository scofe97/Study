package com.dsg.dockercomposetest.service;

import com.dsg.dockercomposetest.dto.UserDto;
import com.dsg.dockercomposetest.entity.UserEntity;
import com.dsg.dockercomposetest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity create(UserDto userDto) {
        
        UserEntity userEntity = UserEntity.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
        
        return userRepository.save(userEntity);
    }

    public UserEntity readOne(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
