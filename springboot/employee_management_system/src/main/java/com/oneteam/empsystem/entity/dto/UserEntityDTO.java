package com.oneteam.empsystem.entity.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDTO {

    private Long id;
    private String username;
    private String password;
    private String role;

}
