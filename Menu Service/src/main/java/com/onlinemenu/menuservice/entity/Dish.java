package com.onlinemenu.menuservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dishId;

    private Long categoryId;
    private String name;
    private double price;

    @Column(length=2000)
    private String description;
//    private boolean vegan;
//    private boolean lowSalt;

    @Column(length=2000)
    private String imageUrl;
}
