package com.oneteam.empsystem.entity.pojo;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "users") // ==> users: is the default name s_security deal with, if you changet it, u must provide queries in Config_class to tell spring how to fetch the data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(name="username", length = 50)
    private String username;

    @Column(name = "password", length = 70) // ==> long for Bcrypted passwords
    private String password;
    
    @Column(name = "enabled")
    private boolean enabled;

    // private String role;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    public UserEntity(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    
    
    

}
