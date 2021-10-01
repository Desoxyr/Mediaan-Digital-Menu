package com.onlinemenu.menuservice.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private Long ingredientId;
    private int amount;
    private String name;

    public Ingredient(Long _ingredientId, int _amount){
        ingredientId = _ingredientId;
        amount = _amount;
    }
}



