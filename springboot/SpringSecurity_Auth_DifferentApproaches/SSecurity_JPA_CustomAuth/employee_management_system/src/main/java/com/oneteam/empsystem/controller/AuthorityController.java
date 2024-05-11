/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.empsystem.controller;

import com.oneteam.empsystem.entity.dto.AuthorityDTO;
import com.oneteam.empsystem.entity.pojo.Authority;
import com.oneteam.empsystem.service.AuthorityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mahmoud
 */
@RestController
@RequestMapping("/api/authorities")
public class AuthorityController {
    
    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public ResponseEntity<List<AuthorityDTO>> getAllAuthorities() {
        List<AuthorityDTO> authorities = authorityService.getAllAuthorities();
        return new ResponseEntity<>(authorities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorityDTO> getAuthorityById(@PathVariable Long id) {
        AuthorityDTO authority = authorityService.getAuthorityById(id);
        return authority != null ?
                new ResponseEntity<>(authority, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<AuthorityDTO> createAuthority(@RequestBody Authority authority) {
        AuthorityDTO savedAuthority = authorityService.saveAuthority(authority);
        return new ResponseEntity<>(savedAuthority, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorityDTO> updateAuthority(@PathVariable Long id, @RequestBody Authority authority) {
        if (authorityService.getAuthorityById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authority.setId(id);
        AuthorityDTO updatedAuthority = authorityService.saveAuthority(authority);
        return new ResponseEntity<>(updatedAuthority, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthority(@PathVariable Long id) {
        authorityService.deleteAuthority(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
