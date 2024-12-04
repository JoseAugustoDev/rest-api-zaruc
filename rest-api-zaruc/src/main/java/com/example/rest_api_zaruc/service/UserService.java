package com.example.rest_api_zaruc.service;

import com.example.rest_api_zaruc.api.model.CustomUserDetails;
import com.example.rest_api_zaruc.api.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();
        User user1 = new User(1, "Jose", "jose@gmail.com", "123");
        User user2 = new User(2, "Augusto", "augusto@gmail.com", "234");
        userList.addAll(Arrays.asList(user1, user2));
    }

    public List<User> getUserList() {
        return userList;
    }

    public Optional<User> getUser(Integer id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public User saveUser(User user) {
        userList.add(user);
        return user;
    }

    public Optional<User> updateUser(Integer id, User updatedUser) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setSenha(updatedUser.getSenha());
                    return user;
                });
    }

    public boolean deleteUser(Integer id) {
        return userList.removeIf(user -> user.getId() == id);
    }

    public UserDetails getUserByLogin(String login) {
        return userList
                .stream()
                .filter(user -> user.getEmail().equals(login))
                .findFirst()
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com este login: " + login));
    }

}
