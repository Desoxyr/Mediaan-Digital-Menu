package com.onlinemenu.menuservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long dishId;
    private Long ingredientId;
    private int amount;

    public DishIngredient(long _dishId, long _ingredientId, int amount) {
        dishId = _dishId;
        ingredientId = _ingredientId;
        this.amount = amount;
    }
}
