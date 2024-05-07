package com.oneteam.empsystem.entity.mapper;

import com.oneteam.empsystem.entity.dto.UserEntityDTO;
import com.oneteam.empsystem.entity.pojo.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserMapper implements Function<UserEntity, UserEntityDTO> {

    @Override
    public UserEntityDTO apply(UserEntity userEntity) {
        UserEntityDTO userEntityDTO = new UserEntityDTO(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRole()
        );
        return userEntityDTO;
    }

}
