package fr.simplon.api.controllers;

import fr.simplon.api.models.Card;
import fr.simplon.api.models.Product;
import fr.simplon.api.repositories.CardRepository;
import fr.simplon.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController {


    private final CardRepository cardRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CardController(CardRepository cardRepository, ProductRepository productRepository) {
        this.cardRepository = cardRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable int id) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isPresent()) {
            return ResponseEntity.ok(card.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card savedCard = cardRepository.save(card);
        return ResponseEntity.status(201).body(savedCard);
    }


    @PutMapping("/{id}/products")
    public ResponseEntity<Card> addProductsToCard(@PathVariable int id, @RequestBody List<Integer> productIds) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Card card = optionalCard.get();
        List<Product> products = productRepository.findAllById(productIds);
        int sumOfPrice = 0;
        for (Product product : products) {
            sumOfPrice += product.getPrice();
        }

        card.setSumOfProductsPrice(sumOfPrice);
        productRepository.saveAll(products);
        card.getProducts().addAll(products);
        Card updatedCard = cardRepository.save(card);

        return ResponseEntity.ok(updatedCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable int id, @RequestBody Card cardDetails) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.setCreationDate(cardDetails.getCreationDate());
            card.setProducts(cardDetails.getProducts());
            Card updatedCard = cardRepository.save(card);
            return ResponseEntity.ok(updatedCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> getCardProducts(@PathVariable int id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalCard.get().getProducts());

    }

    @GetMapping("/{id}/products/price")
    public ResponseEntity<Integer> getprice(@PathVariable int id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalCard.get().getSumOfProductsPrice());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable int id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
