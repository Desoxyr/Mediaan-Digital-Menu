package com.onlinemenu.menuservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinemenu.menuservice.entity.Category;
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

public class CategoryControllerTest {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldSaveCategory() throws Exception {

        Category categoryToPost = new Category(1L,"Vis","Vis producten","https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png");

        String categoryAsString = mapper.writeValueAsString(categoryToPost);

        mvc.perform(post("/menu/categories/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryAsString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.name").value("Vis"))
                .andExpect(jsonPath("$.description").value("Vis producten"))
                .andExpect(jsonPath("$.imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));
    }

    @Test
    void shouldSaveMultipleCategories() throws Exception {

        List<Category> categoriesToPost = new ArrayList<>();
        categoriesToPost.add(new Category(1L,"Vis","Vis producten","https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));
        categoriesToPost.add(new Category(2L,"Vlees","Vlees producten","https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));

        String categoriesAsString = mapper.writeValueAsString(categoriesToPost);

        mvc.perform(post("/menu/categories/create-categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoriesAsString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryId").value(1L))
                .andExpect(jsonPath("$[0].name").value("Vis"))
                .andExpect(jsonPath("$[0].description").value("Vis producten"))
                .andExpect(jsonPath("$[0].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"))
                .andExpect(jsonPath("$[1].categoryId").value(2L))
                .andExpect(jsonPath("$[1].name").value("Vlees"))
                .andExpect(jsonPath("$[1].description").value("Vlees producten"))
                .andExpect(jsonPath("$[1].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));
    }

    @Test
    void shouldGetCategoryByName() throws Exception {
        mvc.perform(get("/menu/categories/name/Vlees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.name").value("Vlees"))
                .andExpect(jsonPath("$.description").value("Vlees producten"))
                .andExpect(jsonPath("$.imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));
    }

    @Test
    void shouldGetCategoryById() throws Exception {
        mvc.perform(get("/menu/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.name").value("Vlees"))
                .andExpect(jsonPath("$.description").value("Vlees producten"))
                .andExpect(jsonPath("$.imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));
    }

    @Test
    void shouldDeleteCategoryById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/menu/categories/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllCategories() throws Exception {
        mvc.perform(get("/menu/categories/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryId").value(1L))
                .andExpect(jsonPath("$[0].name").value("Vlees"))
                .andExpect(jsonPath("$[0].description").value("Vlees producten"))
                .andExpect(jsonPath("$[0].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"))
                .andExpect(jsonPath("$[1].categoryId").value(2L))
                .andExpect(jsonPath("$[1].name").value("Fruit"))
                .andExpect(jsonPath("$[1].description").value("Fruit producten"))
                .andExpect(jsonPath("$[1].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"))
                .andExpect(jsonPath("$[2].categoryId").value(3L))
                .andExpect(jsonPath("$[2].name").value("Groenten"))
                .andExpect(jsonPath("$[2].description").value("Groenten producten"))
                .andExpect(jsonPath("$[2].imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));
    }

    /*@Test
    void shouldUpdateCategory() throws Exception {
        Category categoryOrder = new Category(1L,"Vis","Vis producten","https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png");
        String CategoryAsString = mapper.writeValueAsString(categoryOrder);

        mvc.perform(put("/menu/category/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CategoryAsString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.name").value("Vis"))
                .andExpect(jsonPath("$.description").value("Vis producten"))
                .andExpect(jsonPath("$.imageUrl").value("https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png"));

    }*/

}
