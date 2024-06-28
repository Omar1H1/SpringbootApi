package fr.simplon.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import fr.simplon.api.models.Product;
import java.util.List;

@Entity
@Data
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;


    private List<String> products;

    private Integer priceToPay;

    public Order() {}
}
