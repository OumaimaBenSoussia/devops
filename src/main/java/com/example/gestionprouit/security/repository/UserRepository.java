package com.example.gestionprouit.security.repository;

import com.example.gestionprouit.security.entities.AppRole;
import com.example.gestionprouit.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <AppUser, String> {

    public AppUser findAppUserByUsername(String username);

}
