package com.example.gestionprouit.security.service;

import com.example.gestionprouit.security.entities.AppUser;

public interface IAccountService {

    public void addRole(String role);
    public void addUser(String username, String password, String mail);
    public void addRoleToUser(String username,String role);
    public AppUser loadUserByUsername(String username);
}
