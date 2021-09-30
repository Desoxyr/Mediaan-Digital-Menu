package com.example.tableservice.repository;

import com.example.tableservice.entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRespository extends JpaRepository<Tables, Long> {
}
