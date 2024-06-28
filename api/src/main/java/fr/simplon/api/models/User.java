package fr.simplon.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;


import java.util.List;

@Entity
@Data
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;
    private String name;
    private String email;

    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Order> orders;

    public User() {}

}

