package com.onlinemenu.menuservice.service;

import com.onlinemenu.menuservice.VO.DishVO;
import com.onlinemenu.menuservice.entity.Dish;
import com.onlinemenu.menuservice.entity.DishIngredient;
import com.onlinemenu.menuservice.repository.DishIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishIngredientService {

    @Autowired
    private DishIngredientRepository dishIngredientRepository;

    public void saveIngredients(List<DishIngredient> ingredients) {
        dishIngredientRepository.saveAll(ingredients);
    }

    public List<DishIngredient> getDishIngredients(Long dishId) {
        return dishIngredientRepository.findByDishId(dishId);
    }
}
