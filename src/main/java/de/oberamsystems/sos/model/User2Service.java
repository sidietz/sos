package de.oberamsystems.sos.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User2Service {
	@Autowired
    private User2Repository user2Repository;

    public User2 saveUser(User2 user2) {
        return user2Repository.save(user2);
    }

    public Iterable<User2> getAllUsers() {
        return user2Repository.findAll();
    }

    public User2 getUserById(Long id) {
        return user2Repository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id) {
        user2Repository.deleteById(id);
    }
}
