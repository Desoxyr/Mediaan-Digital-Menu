package com.onlinemenu.menuservice.repository;

import com.onlinemenu.menuservice.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByName(String categoryName);

    List<Dish> findByCategoryId(Long categoryId);
}
