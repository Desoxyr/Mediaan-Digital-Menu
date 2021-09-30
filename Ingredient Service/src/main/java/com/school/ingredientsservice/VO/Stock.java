package com.school.ingredientsservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Stock {

    private Long stockId;
    private int stockAmount ;
}
