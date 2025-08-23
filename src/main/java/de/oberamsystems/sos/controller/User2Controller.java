package de.oberamsystems.sos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.oberamsystems.sos.model.User2;
import de.oberamsystems.sos.model.User2Service;

@RestController
@RequestMapping("/api/users")
public class User2Controller {

    @Autowired
    private User2Service user2Service;

    @PostMapping
    public User2 createUser(@RequestBody User2 user2) {
        return user2Service.saveUser(user2);
    }

    @GetMapping
    public Iterable<User2> getAllUsers() {
        return user2Service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User2 getUserById(@PathVariable Long id) {
        return user2Service.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        user2Service.deleteUserById(id);
    }
}