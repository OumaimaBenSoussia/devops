package com.example.gestionprouit.security.repository;

import com.example.gestionprouit.security.entities.AppRole;
import com.example.gestionprouit.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <AppRole,String > {
}
