package com.onlinemenu.menuservice.controller;

import com.onlinemenu.menuservice.VO.DishVO;
import com.onlinemenu.menuservice.VO.Ingredient;
import com.onlinemenu.menuservice.entity.Dish;
import com.onlinemenu.menuservice.entity.DishIngredient;
import com.onlinemenu.menuservice.service.DishIngredientService;
import com.onlinemenu.menuservice.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishIngredientService dishIngredientService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/all")
    public List<Dish> dishes() {
        return dishService.getAllDishes();
    }

    @PostMapping("/all-in-shopping-cart")
    public List<Dish> getAllDishes(@RequestBody List<Long> dishIds) {
        return dishService.getAllDishes(dishIds);
    }

    @PostMapping("/create")
    public Dish saveDish(@RequestBody DishVO dishVO) {

        // Add Dish
        Dish dish = new Dish(null, dishVO.getCategoryId(), dishVO.getName(),
                dishVO.getPrice(), dishVO.getDescription(), dishVO.getImageUrl());

        Dish savedDish = dishService.saveDish(dish);

        // Save Ingredients
        // Convert ingredients to DishIngredients
        var dishIngredients = dishVO.getIngredients().stream().map(
                x -> new DishIngredient(savedDish.getDishId(), x.getIngredientId(), x.getAmount()))
                .collect(Collectors.toList());

        dishIngredientService.saveIngredients(dishIngredients);

        return savedDish;
    }

    // TODO: This should return DishDto. Optionally create a new method for it.
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
    public List<Dish> findDishesByCategoryName(@PathVariable String name) {
        return dishService.findDishesByCategoryName(name);
    }

    @GetMapping("/category-available/{name}")
    public List<DishVO> availableDishes(@PathVariable String name) {

        var allDishes = dishService.findDishesByCategoryName(name);
        var allDishesWithIngredients = getDishesWithIngredients(allDishes);

        // Call all from Ingredient Service to retrieve stock.
        String allIngredientsUrl = "http://localhost:9191/ingredient/all";
        Ingredient[] stock = restTemplate.getForObject(allIngredientsUrl, Ingredient[].class);

        // Check which dishes can be made with current stock.
        var availableDishes = new ArrayList<DishVO>();
        for (var dish : allDishesWithIngredients) {
            if (checkStockAvailability(dish, stock)){
                availableDishes.add(dish);
            }
        }

        return availableDishes;
    }

    @PostMapping("/dishes-with-ingredients")
    public DishVO[] findDishesWithIngredients(@RequestBody List<Long> dishIds) {

        //Fetch dishes based on dishIds
        List<Dish> dishes = dishService.getAllDishes(dishIds);
        return getDishesWithIngredients(dishes);
    }


    private DishVO[] getDishesWithIngredients(List<Dish> dishes) {

        //Per Dish, look up the ingredients and add the full dish to dishVOs
        var dishVOs = new ArrayList<DishVO>();
        for (var dish : dishes) {
            var dishIngredients = dishIngredientService.getDishIngredients(dish.getDishId());

            //Map DishIngredients to Ingredients (without name)
            var ingredients = dishIngredients.stream().map(
                    x -> new Ingredient(x.getIngredientId(), x.getAmount()))
                    .collect(Collectors.toList());

            dishVOs.add(new DishVO(dish.getDishId(), dish.getCategoryId(),
                    dish.getName(), dish.getPrice(), dish.getDescription(), dish.getImageUrl(), ingredients));
        }

        return dishVOs.toArray(new DishVO[0]);
    }

    private boolean checkStockAvailability(DishVO dishVO, Ingredient[] stock) {

        for (var ingredient : dishVO.getIngredients()) {
            var ingredientId = ingredient.getIngredientId();

            // Find the ingredient based on ID. Null if it cannot be found.
            var stockIngredient = Arrays.stream(stock).filter(i -> i.getIngredientId().equals(ingredientId)).findFirst().orElse(null);

            if (stockIngredient == null || stockIngredient.getAmount() < ingredient.getAmount()){
                return false;
            }
        }
        return true;
    }

}
