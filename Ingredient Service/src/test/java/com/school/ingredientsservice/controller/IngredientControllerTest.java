package com.school.ingredientsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.ingredientsservice.entity.Ingredient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;



@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class IngredientControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void saveIngredient() throws Exception {
        Ingredient ingredientToPost = new Ingredient("Frikandel", 20);
        String ingredientAsString = mapper.writeValueAsString(ingredientToPost);

        mvc.perform(post("/ingredient/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ingredientAsString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Frikandel"))
                .andExpect(jsonPath("$.amount").value(20));
    }

    @Test
    void shouldGetAllIngredients() throws Exception {
        mvc.perform(get("/ingredient/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].ingredientId").value(1L))
                .andExpect(jsonPath("$.[0].name").value("Steak"))
                .andExpect(jsonPath("$.[0].amount").value(10))
                .andExpect(jsonPath("$.[1].ingredientId").value(2L))
                .andExpect(jsonPath("$.[1].name").value("Rice"))
                .andExpect(jsonPath("$.[1].amount").value(100))
                .andExpect(jsonPath("$.[2].ingredientId").value(3L))
                .andExpect(jsonPath("$.[2].name").value("Potatoes"))
                .andExpect(jsonPath("$.[2].amount").value(50));
    }

    @Test
    void getAllIngredientsInOrder() throws Exception {
        mvc.perform(get("/ingredient/allInOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].ingredientId").value(3L))
                .andExpect(jsonPath("$.[0].name").value("Potatoes"))
                .andExpect(jsonPath("$.[0].amount").value(50))
                .andExpect(jsonPath("$.[1].ingredientId").value(2L))
                .andExpect(jsonPath("$.[1].name").value("Rice"))
                .andExpect(jsonPath("$.[1].amount").value(100))
                .andExpect(jsonPath("$.[2].ingredientId").value(1L))
                .andExpect(jsonPath("$.[2].name").value("Steak"))
                .andExpect(jsonPath("$.[2].amount").value(10));
    }

    @Test
    void deleteIngredientById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/ingredient/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void subtractIngredient() throws Exception{

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(2L, 20, null));
        String ingredientListString = mapper.writeValueAsString(ingredients);

        mvc.perform(post("/ingredient/subtract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ingredientListString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/ingredient/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].ingredientId").value(1L))
                .andExpect(jsonPath("$.[0].name").value("Steak"))
                .andExpect(jsonPath("$.[0].amount").value(10))
                .andExpect(jsonPath("$.[1].ingredientId").value(2L))
                .andExpect(jsonPath("$.[1].name").value("Rice"))
                .andExpect(jsonPath("$.[1].amount").value(80))
                .andExpect(jsonPath("$.[2].ingredientId").value(3L))
                .andExpect(jsonPath("$.[2].name").value("Potatoes"))
                .andExpect(jsonPath("$.[2].amount").value(50));

    }

    @Test
    void updateIngredient() throws Exception {
        Ingredient ingredient = new Ingredient(1L, 20, "Banaan");
        String IngredientAsString = mapper.writeValueAsString(ingredient);

        mvc.perform(put("/ingredient/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IngredientAsString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ingredientId").value(1L))
                .andExpect(jsonPath("$.amount").value(20))
                .andExpect(jsonPath("$.name").value("Banaan"));
    }

}
