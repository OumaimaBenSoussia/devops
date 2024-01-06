package com.example.gestionprouit.security.service;

import com.example.gestionprouit.security.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    IAccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        AppUser user=accountService.loadUserByUsername(username);
        List<GrantedAuthority> authorityList=new ArrayList<>();
        user.getRoles().forEach(e->authorityList.add(new SimpleGrantedAuthority(e.getRole())));
        return new User(user.getUsername(), user.getPassword(),authorityList);
    }
}
