package com.example.rest_api_zaruc.api.controller;

import com.example.rest_api_zaruc.api.model.User;
import com.example.rest_api_zaruc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Optional<User> getUser(@RequestParam Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/AllUsers")
    public List<User> getAllUsers() {
        return userService.getUserList();
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User saveUser = userService.saveUser(user);

        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {

        Optional<User> user = userService.updateUser(id, updatedUser);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário com ID " + id + " não encontrado.");
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam Integer id) {

        boolean isDeleted = userService.deleteUser(id);

        if (isDeleted) {
            return ResponseEntity.ok("Usuário com ID " + id + " foi excluído com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário com ID " + id + " não encontrado.");
        }
    }


}
