package com.school.ingredientsservice.service;

import com.school.ingredientsservice.entity.Ingredient;
import com.school.ingredientsservice.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Ingredient> getAllIngredientsInOrder() {
        return ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredientById(Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }

    public Ingredient findIngredientById(Long ingredientId) {
        return ingredientRepository.findByIngredientId(ingredientId);
    }

    public void subtractStock(List<Ingredient> ingredients) {
        for (var ingredient : ingredients) {
            ingredientRepository.subtractStock(ingredient.getAmount(), ingredient.getIngredientId());
        }
    }
}
