package com.example.rest_api_zaruc.api.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if ("admin".equals(username)) {

            return User.builder()
                    .username("admin")
                    .password("$2a$10$7GhRgr0b0q00g9j2eA2gIu/dfFwqNwnfNqEOR9uFf4im.JDAq0LSi")
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
}
