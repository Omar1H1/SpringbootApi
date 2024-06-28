package fr.simplon.api.repositories;

import fr.simplon.api.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
//    @Query("SELECT SUM(p.price) FROM Products p WHERE p.card.id = ?1")
//    Optional<Integer> getSumOfProductPricesByCardId(int cardId);
}
