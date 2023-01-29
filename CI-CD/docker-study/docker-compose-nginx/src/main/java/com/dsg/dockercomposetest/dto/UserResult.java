package com.dsg.dockercomposetest.dto;

import com.dsg.dockercomposetest.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResult {

    private Long id;
    private String username;
    private String password;

    public UserResult(UserEntity users) {
        this.id = users.getId();
        this.username = users.getUsername();
        this.password = users.getPassword();
    }
}
