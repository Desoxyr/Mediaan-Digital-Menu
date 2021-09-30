package com.example.tableservice.controller;

import com.example.tableservice.entity.Tables;
import com.example.tableservice.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;

    @PostMapping("/create")
    public Tables saveCategory(@RequestBody Tables tables){
        return tableService.saveTable(tables);
    }

    @GetMapping("/{id}")
    public Optional<Tables> findCategoryById(@PathVariable("id") Long categoryId){
        return tableService.findTableById(categoryId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTableById(@PathVariable("id") Long categoryId){
        tableService.deleteTableById(categoryId);
    }

    @GetMapping("/all")
    public List<Tables> getAllTable(){
        return tableService.GetAllTables();
    }

    @PutMapping("/update")
    public Tables updateTable(@RequestBody Tables category) {
        return tableService.saveTable(category);
    }
}
