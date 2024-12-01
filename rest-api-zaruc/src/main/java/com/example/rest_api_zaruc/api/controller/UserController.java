package com.example.rest_api_zaruc.api.controller;

import com.example.rest_api_zaruc.api.model.User;
import com.example.rest_api_zaruc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Optional<User> getUser(@RequestParam Integer id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);  // Método para salvar o usuário (adicionado)
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);  // Método para atualizar o usuário (adicionado)
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);  // Método para excluir o usuário (adicionado)
    }
}
