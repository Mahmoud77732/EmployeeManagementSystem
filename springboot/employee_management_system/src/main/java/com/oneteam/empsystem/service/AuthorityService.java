/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.dto.AuthorityDTO;
import com.oneteam.empsystem.entity.dto.UserEntityDTO;
import com.oneteam.empsystem.entity.mapper.AuthorityMapper;
import com.oneteam.empsystem.entity.mapper.UserMapper;
import com.oneteam.empsystem.entity.pojo.Authority;
import com.oneteam.empsystem.entity.pojo.UserEntity;
import com.oneteam.empsystem.repo.AuthorityRepo;
import com.oneteam.empsystem.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mahmoud
 */
@Service
@Transactional
public class AuthorityService {
    
    @Autowired
    private AuthorityRepo authorityRepo;

    @Autowired
    private AuthorityMapper authorityMapper;


    //TODO using DTO
    public List<AuthorityDTO> getAllAuthorities() {
        List<Authority> authorities = authorityRepo.findAll();
        return authorities.stream()
                .map(authorityMapper)
                .collect(Collectors.toList());
    }

    public AuthorityDTO getAuthorityById(Long id) {
        Authority authority = authorityRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Authority not found with id: " + id));
        return authorityMapper.apply(authority);
    }

    public AuthorityDTO saveAuthority(Authority authority) {
        Authority savedAuthority = authorityRepo.save(authority);
        return authorityMapper.apply(savedAuthority);
    }

    public void deleteAuthority(Long id) {
        authorityRepo.deleteById(id);
    }
    
}
