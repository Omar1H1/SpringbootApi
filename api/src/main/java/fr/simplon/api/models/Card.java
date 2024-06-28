package fr.simplon.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date creationDate;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    @Column(nullable = false)
    @NonNull
    private Integer sumOfProductsPrice;

    public Card () {

    }
}
