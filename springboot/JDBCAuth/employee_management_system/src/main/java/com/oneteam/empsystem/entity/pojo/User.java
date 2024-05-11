package com.oneteam.empsystem.entity.pojo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "ourusers") // ==> users: is the default name s_security deal with, if you changet it, u must provide queries in Config_class to tell spring how to fetch the data
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="username", length = 100)
    private String userName;

    @Column(name = "password", length = 100) // ==> long for Bcrypted passwords
    private String password;
    
    @Column(name = "enabled")
    private boolean enabled;

    // private String role;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ourusers_roles",
            joinColumns = @JoinColumn(name = "ouruser_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    
    public User(String userName, String password, boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

}
