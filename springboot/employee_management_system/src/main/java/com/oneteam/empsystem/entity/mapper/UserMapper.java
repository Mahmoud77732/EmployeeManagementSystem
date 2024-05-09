package com.oneteam.empsystem.entity.mapper;

import com.oneteam.empsystem.entity.dto.AuthorityDTO;
import com.oneteam.empsystem.entity.dto.UserEntityDTO;
import com.oneteam.empsystem.entity.pojo.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserMapper implements Function<UserEntity, UserEntityDTO> {

    @Override
    public UserEntityDTO apply(UserEntity userEntity) {
        UserEntityDTO userEntityDTO = new UserEntityDTO(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.getAuthorities().stream()
                    .map(authority -> {
                        return new AuthorityDTO(
                                authority.getId(),
                                authority.getAuthority()
                        );
                    }).collect(Collectors.toSet()));
        return userEntityDTO;
    }

}
