/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.empsystem.entity.mapper;

import com.oneteam.empsystem.entity.dto.AuthorityDTO;
import com.oneteam.empsystem.entity.pojo.Authority;
import java.util.function.Function;
import org.springframework.stereotype.Service;

/**
 *
 * @author mahmoud
 */
@Service
public class AuthorityMapper implements Function<Authority, AuthorityDTO> {

    @Override
    public AuthorityDTO apply(Authority authority) {
        AuthorityDTO authorityDTO = new AuthorityDTO(
                authority.getId(),
                authority.getAuthority()
        );
        return authorityDTO;
    }

}