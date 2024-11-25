package com.example.rest_api_zaruc;

import com.example.rest_api_zaruc.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Long, User> userDatabase = new HashMap<>();
    private long userIdCounter = 1;

    // Criar usuário
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(userIdCounter++);
        userDatabase.put(user.getId(), user);
        return ResponseEntity.ok(user);
    }

    // Ler todos os usuários
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(new ArrayList<>(userDatabase.values()));
    }

    // Ler usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userDatabase.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userDatabase.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        updatedUser.setId(id);
        userDatabase.put(id, updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userDatabase.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        userDatabase.remove(id);
        return ResponseEntity.noContent().build();
    }
}
