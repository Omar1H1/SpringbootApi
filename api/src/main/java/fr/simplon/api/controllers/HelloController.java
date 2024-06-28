package fr.simplon.api.controllers;


import fr.simplon.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/{id}")
    public String hello(@PathVariable int id) {
////        if(userRepository.findById(id).isEmpty()) {
////            throw NoSuchElementException;
////        }
////        return "Hello, " + userRepository.findById(id).get().getName();
        try {
            userRepository.findById(id)
                    .orElseThrow(() ->
                            new NoSuchElementException("User with id " + id + " is not found"));

            return "Hello, " + userRepository.findById(id).get().getName();
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
    }

}
