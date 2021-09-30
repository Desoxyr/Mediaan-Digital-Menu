package com.school.ingredientsservice.VO;

import com.school.ingredientsservice.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Ingredient ingredient;
    private Stock stock;

}
