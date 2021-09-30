package com.onlinemenu.menuservice.controller;

import com.onlinemenu.menuservice.entity.Dish;
import com.onlinemenu.menuservice.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/all")
    public List<Dish> dishes() {
        return dishService.getAllDishes();
    }

    @PostMapping("/all-in-shopping-cart")
    public List<Dish> getAllDishesInShoppingCart(@RequestBody List<Long> dishIds)
    {
        return dishService.getAllDishesInShoppingCart(dishIds);
    }

    @PostMapping("/create")
    public Dish saveDish(@RequestBody Dish dish) {
        return dishService.saveDish(dish);
    }

    @GetMapping("/{id}")
    public Optional<Dish> findDishById(@PathVariable("id") Long dishId) {
        return dishService.findDishById(dishId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDishById(@PathVariable("id") Long dishId) {
        dishService.deleteDishById(dishId);
    }

    @PutMapping("/update/{id}")
    public Dish updateDish(@RequestBody Dish dish) {
        return dishService.updateDish(dish);
    }

    @GetMapping("/category/{name}")
    public List<Dish> findDishesByCategoryName(@PathVariable String name){
        return dishService.findDishesByCategoryName(name);
    }
}
