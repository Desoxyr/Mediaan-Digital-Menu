package com.example.tableservice.service;

import com.example.tableservice.entity.Tables;
import com.example.tableservice.repository.TableRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {
    @Autowired
    private TableRespository tableRepository;

    public List<Tables> saveTable(List<Tables> tables ){ return tableRepository.saveAll(tables);}

    public Tables saveTable(Tables tables) {
        return tableRepository.save(tables);
    }

    public Optional<Tables> findTableById(Long tableId) {
        return tableRepository.findById(tableId);
    }

    public void deleteTableById(Long tableId) {
        tableRepository.deleteById(tableId);
    }

    public List<Tables> GetAllTables() {
        return tableRepository.findAll();
    }

}
