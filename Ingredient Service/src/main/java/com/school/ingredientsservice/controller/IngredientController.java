package com.school.ingredientsservice.controller;

import com.school.ingredientsservice.entity.Ingredient;
import com.school.ingredientsservice.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/all")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/allInOrder")
    public List<Ingredient> getAllIngredientsInOrder() {
        return ingredientService.getAllIngredientsInOrder();
    }

    @PostMapping("/create")
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.saveIngredient(ingredient);
    }


    @PutMapping("/update/{id}")
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient, @PathVariable Long id) {
        return ingredientService.updateIngredient(ingredient, id);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteIngredientById(@PathVariable("id") Long ingredientId) {
        ingredientService.deleteIngredientById(ingredientId);
    }

    @PostMapping("/subtract")
    public void subtractIngredient(@RequestBody List<Ingredient> ingredients) {
        ingredientService.subtractStock(ingredients);
    }
}
