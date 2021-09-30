package com.example.tableservice.controller;

import com.example.tableservice.TableServiceApplication;
import com.example.tableservice.entity.Tables;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TablesController {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void saveTable() throws Exception{
        Tables tables = new Tables(1L,1);
        String tableAsString = mapper.writeValueAsString(tables);

        mvc.perform(post("/table/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(tableAsString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tableId").value(1L))
                .andExpect(jsonPath("$.tableNumber").value(1));
    }

    @Test
    void shouldGetAllTables() throws Exception{
        mvc.perform(get("/table/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].tableId").value(1L))
                .andExpect(jsonPath("$.[0].tableNumber").value(1))
                .andExpect(jsonPath("$.[1].tableId").value(2L))
                .andExpect(jsonPath("$.[1].tableNumber").value(2))
                .andExpect(jsonPath("$.[2].tableId").value(3L))
                .andExpect(jsonPath("$.[2].tableNumber").value(3));
    }

    @Test
    void shouldDeleteTableById() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .delete("/table/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
