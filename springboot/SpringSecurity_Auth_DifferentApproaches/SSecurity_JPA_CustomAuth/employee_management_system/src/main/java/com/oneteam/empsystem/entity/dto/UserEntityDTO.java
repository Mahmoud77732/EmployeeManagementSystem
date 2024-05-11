package com.oneteam.empsystem.entity.dto;

import java.util.Set;

public record UserEntityDTO (
    String username,
    String password,
    boolean enabled,
    Set<AuthorityDTO> authorities

){}
