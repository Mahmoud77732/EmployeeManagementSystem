package com.oneteam.empsystem.entity.dto;

import java.util.List;

public record UserEntityDTO (
    Long id,
    String username,
    String password,
    List<AuthorityDTO> authorities

){}
