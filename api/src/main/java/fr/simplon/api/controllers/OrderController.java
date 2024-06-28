package fr.simplon.api.controllers;

import fr.simplon.api.helpers.CreateOrder;
import fr.simplon.api.models.Order;
import fr.simplon.api.models.Product;
import fr.simplon.api.repositories.CardRepository;
import fr.simplon.api.repositories.OrderRepository;
import fr.simplon.api.repositories.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderRepository orderRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderController(CardRepository cardRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders () {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> getOrderById (@PathVariable int id) {
        return ResponseEntity.ok(orderRepository.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Order> addOrder(@RequestBody CreateOrder orderToCreate) {
        ArrayList<String> productsList = new ArrayList<>();
        Order order = new Order();
        String username = userRepository.findById(orderToCreate.getUserId()).get().getUsername();
        List<Product> products = cardRepository.findById(orderToCreate.getCardId()).get().getProducts();
        Integer price = cardRepository.findById(orderToCreate.getCardId()).get().getSumOfProductsPrice();

        for(Product product : products) {
            productsList.add(product.getName());
        }
        order.setProducts(productsList);
        order.setUsername(username);
        order.setPriceToPay(price);
        return ResponseEntity.ok(orderRepository.save(order));
    }
}
