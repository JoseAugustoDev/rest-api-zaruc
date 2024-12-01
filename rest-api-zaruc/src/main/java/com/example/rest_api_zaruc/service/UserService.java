package com.example.rest_api_zaruc.service;

import com.example.rest_api_zaruc.api.model.User;
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

    public Optional<User> getUser(Integer id) {
        return userList.stream()
                .filter(user -> user.getId() == id)  // Usando '==' para comparar o id
                .findFirst();
    }

    public User saveUser(User user) {
        userList.add(user);
        return user;
    }

    public User updateUser(User user) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == user.getId()) {  // Usando '==' para comparar o id
                userList.set(i, user);
                return user;
            }
        }
        return null; // Se não encontrar, retorna null
    }

    public void deleteUser(Integer id) {
        userList.removeIf(user -> user.getId() == id);  // Usando '==' para comparar o id
    }
}
