package com.example.gestionprouit.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AppUser {

    @Id
    private String id;
    @Column(unique = true)
    private String username;
    private String password;
    private String mail;
    @ManyToMany(fetch = FetchType.EAGER) //manytomany par défaut lazy
    private List<AppRole> roles;
}
