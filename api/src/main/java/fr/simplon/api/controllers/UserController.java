package fr.simplon.api.controllers;

import fr.simplon.api.models.User;
import fr.simplon.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id);
        } else {
            throw new NoSuchElementException("Provided id : " + id + " is not an actual user");
        }

    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

}
