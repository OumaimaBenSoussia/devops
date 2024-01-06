package com.example.gestionprouit.security;

import com.example.gestionprouit.security.service.UserDetailsServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    PasswordEncoder passwordEncoder;
    UserDetailsServiceImp userDetailsService;

    //@Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user") .password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin") .password(passwordEncoder.encode("1234")).roles("ADMIN","USER").build()

                );
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        //security.formLogin(form->form.disable());
        security.formLogin(form->form.permitAll());
        security.httpBasic(Customizer.withDefaults());
        security.authorizeHttpRequests(authorize->authorize.requestMatchers("/user/**").hasAnyAuthority("USER"));
        security.authorizeHttpRequests(authorize->authorize.requestMatchers("/admin/**").hasAnyAuthority("ADMIN"));
        security.exceptionHandling(authorize->authorize.accessDeniedPage("/errorPage"));
        security.authorizeHttpRequests(authorize->authorize.anyRequest().authenticated());
        security.userDetailsService(userDetailsService);
        security.csrf(c->c.disable());

        return security.build();
    }
}
