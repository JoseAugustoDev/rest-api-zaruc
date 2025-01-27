package com.example.rest_api_zaruc.api.repository;

import com.example.rest_api_zaruc.api.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    void save(User user);
}
