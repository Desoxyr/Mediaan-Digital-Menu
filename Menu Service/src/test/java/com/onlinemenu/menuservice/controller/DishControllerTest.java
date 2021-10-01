package com.onlinemenu.menuservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinemenu.menuservice.VO.DishVO;
import com.onlinemenu.menuservice.VO.Ingredient;
import com.onlinemenu.menuservice.entity.Dish;
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

import javax.persistence.criteria.CriteriaBuilder;
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

public class DishControllerTest {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldGetAllDishes() throws Exception {
        mvc.perform(get("/menu/dishes/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dishId").value(1L))
                .andExpect(jsonPath("$[0].categoryId").value(1L))
                .andExpect(jsonPath("$[0].name").value("Biefstuk"))
                .andExpect(jsonPath("$[0].price").value(7.00))
                .andExpect(jsonPath("$[0].description").value("Een stuk biefstuk"))
                .andExpect(jsonPath("$[0].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"))
                .andExpect(jsonPath("$[1].dishId").value(2L))
                .andExpect(jsonPath("$[1].categoryId").value(1L))
                .andExpect(jsonPath("$[1].name").value("Kip"))
                .andExpect(jsonPath("$[1].price").value(5.00))
                .andExpect(jsonPath("$[1].description").value("Een stuk kip"))
                .andExpect(jsonPath("$[1].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"))
                .andExpect(jsonPath("$[2].dishId").value(3L))
                .andExpect(jsonPath("$[2].categoryId").value(2L))
                .andExpect(jsonPath("$[2].name").value("Bloemkool"))
                .andExpect(jsonPath("$[2].price").value(3.00))
                .andExpect(jsonPath("$[2].description").value("Een bord met bloemkool"))
                .andExpect(jsonPath("$[2].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"))
                .andExpect(jsonPath("$[3].dishId").value(4L))
                .andExpect(jsonPath("$[3].categoryId").value(2L))
                .andExpect(jsonPath("$[3].name").value("Wortels"))
                .andExpect(jsonPath("$[3].price").value(4.00))
                .andExpect(jsonPath("$[3].description").value("Een bord met wortels"))
                .andExpect(jsonPath("$[3].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));
    }

    @Test
    void shouldGetAllDishesInShoppingCart() throws Exception {
        List<Long> dishIds = new ArrayList<>();
        dishIds.add(1L);
        dishIds.add(2L);
        dishIds.add(3L);

        String dishIdsAsString = mapper.writeValueAsString(dishIds);

        mvc.perform(post("/menu/dishes/all-in-shopping-cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dishIdsAsString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dishId").value(1L))
                .andExpect(jsonPath("$[0].categoryId").value(1L))
                .andExpect(jsonPath("$[0].name").value("Biefstuk"))
                .andExpect(jsonPath("$[0].price").value(7.00))
                .andExpect(jsonPath("$[0].description").value("Een stuk biefstuk"))
                .andExpect(jsonPath("$[0].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"))
                .andExpect(jsonPath("$[1].dishId").value(2L))
                .andExpect(jsonPath("$[1].categoryId").value(1L))
                .andExpect(jsonPath("$[1].name").value("Kip"))
                .andExpect(jsonPath("$[1].price").value(5.00))
                .andExpect(jsonPath("$[1].description").value("Een stuk kip"))
                .andExpect(jsonPath("$[1].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"))
                .andExpect(jsonPath("$[2].dishId").value(3L))
                .andExpect(jsonPath("$[2].categoryId").value(2L))
                .andExpect(jsonPath("$[2].name").value("Bloemkool"))
                .andExpect(jsonPath("$[2].price").value(3.00))
                .andExpect(jsonPath("$[2].description").value("Een bord met bloemkool"))
                .andExpect(jsonPath("$[2].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));

    }

    @Test
    void shouldSaveDish() throws Exception {

        DishVO dishToPost = new DishVO(1L,1L,"Spek",5.00,"Een stuk spek","https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png", new ArrayList<Ingredient>());

        String dishAsString = mapper.writeValueAsString(dishToPost);

        mvc.perform(post("/menu/dishes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dishAsString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dishId").value(1L))
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.name").value("Spek"))
                .andExpect(jsonPath("$.price").value(5.00))
                .andExpect(jsonPath("$.description").value("Een stuk spek"))
                .andExpect(jsonPath("$.imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));
    }

    @Test
    void shouldGetDishById() throws Exception {
        mvc.perform(get("/menu/dishes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dishId").value(1L))
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.name").value("Biefstuk"))
                .andExpect(jsonPath("$.price").value(7.00))
                .andExpect(jsonPath("$.description").value("Een stuk biefstuk"))
                .andExpect(jsonPath("$.imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));
    }

    @Test
    void deleteDishById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/menu/dishes/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateDish() throws Exception {
        Dish dish = new Dish(1L,1L,"Spek",5.00,"Een stuk spek","https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png");
        String dishAsString = mapper.writeValueAsString(dish);

        mvc.perform(put("/menu/dishes/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dishAsString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dishId").value(1L))
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.name").value("Spek"))
                .andExpect(jsonPath("$.price").value(5.00))
                .andExpect(jsonPath("$.description").value("Een stuk spek"))
                .andExpect(jsonPath("$.imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));
    }

    @Test
    void shouldGetDishesByCategoryName() throws Exception {
        mvc.perform(get("/menu/dishes/category/Vlees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dishId").value(1L))
                .andExpect(jsonPath("$[0].categoryId").value(1L))
                .andExpect(jsonPath("$[0].name").value("Biefstuk"))
                .andExpect(jsonPath("$[0].price").value(7.00))
                .andExpect(jsonPath("$[0].description").value("Een stuk biefstuk"))
                .andExpect(jsonPath("$[0].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"))
                .andExpect(jsonPath("$[1].dishId").value(2L))
                .andExpect(jsonPath("$[1].categoryId").value(1L))
                .andExpect(jsonPath("$[1].name").value("Kip"))
                .andExpect(jsonPath("$[1].price").value(5.00))
                .andExpect(jsonPath("$[1].description").value("Een stuk kip"))
                .andExpect(jsonPath("$[1].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));
    }

    @Test
    void findDishesWithIngredients() throws Exception {

        List<Long> dishIds = new ArrayList<>();
        dishIds.add(1L);
        dishIds.add(2L);

        String dishIdsAsString = mapper.writeValueAsString(dishIds);

        mvc.perform(post("/menu/dishes/dishes-with-ingredients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dishIdsAsString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dishId").value(1L))
                .andExpect(jsonPath("$[0].categoryId").value(1L))
                .andExpect(jsonPath("$[0].name").value("Biefstuk"))
                .andExpect(jsonPath("$[0].price").value(7.00))
                .andExpect(jsonPath("$[0].description").value("Een stuk biefstuk"))
                .andExpect(jsonPath("$[0].ingredients[0].ingredientId").value(1000))
                .andExpect(jsonPath("$[0].ingredients[0].amount").value(10))
                .andExpect(jsonPath("$[0].ingredients[1].ingredientId").value(1001))
                .andExpect(jsonPath("$[0].ingredients[1].amount").value(5))
                .andExpect(jsonPath("$[1].ingredients[0].ingredientId").value(1002))
                .andExpect(jsonPath("$[1].ingredients[0].amount").value(20));
    }
}
