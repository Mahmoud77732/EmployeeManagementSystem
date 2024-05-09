package com.oneteam.empsystem.entity.pojo;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(name="username", length = 50)
    private String username;

    @Column(name = "password", length = 50)
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
