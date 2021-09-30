package com.school.ingredientsservice.repository;

import com.school.ingredientsservice.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByIngredientId(Long ingredientId);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE Ingredient i SET i.amount = i.amount - :amountToSubtract WHERE i.ingredientId = :ingredientId"
    )
    void subtractStock(int amountToSubtract, Long ingredientId);
}
