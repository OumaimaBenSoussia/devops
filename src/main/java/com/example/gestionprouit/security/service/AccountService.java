package com.example.gestionprouit.security.service;

import com.example.gestionprouit.security.entities.AppRole;
import com.example.gestionprouit.security.entities.AppUser;
import com.example.gestionprouit.security.repository.RoleRepository;
import com.example.gestionprouit.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AccountService implements IAccountService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public void addRole(String role) {
        //roleRepository.save(new AppRole(role));
        roleRepository.save(AppRole.builder().role(role).build());

    }

    @Override
    public void addUser(String username, String password, String mail) {
        AppUser user=userRepository.findAppUserByUsername(username);
                if(user!=null) throw new RuntimeException(("user existe"));
        userRepository.save(AppUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .mail(mail)
                .id(UUID.randomUUID().toString())
                .build());
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser user=userRepository.findAppUserByUsername(username);
        AppRole rol=roleRepository.findById(role).orElse(null);
        user.getRoles().add(rol);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return userRepository.findAppUserByUsername(username);
    }


}
