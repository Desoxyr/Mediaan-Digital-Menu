package com.onlinemenu.menuservice.repository;

import com.onlinemenu.menuservice.entity.DishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishIngredientRepository extends JpaRepository<DishIngredient, Long> {
    List<DishIngredient> findByDishId(Long dishId);
}
