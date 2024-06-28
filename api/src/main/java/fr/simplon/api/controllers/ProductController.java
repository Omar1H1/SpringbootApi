package fr.simplon.api.controllers;

import fr.simplon.api.models.Product;
import fr.simplon.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public List<Product> getProductsList () {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById (@PathVariable Integer id) {
        return productRepository.findById(id);
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
