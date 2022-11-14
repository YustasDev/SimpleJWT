package com.example.taco_cloud.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Table
public class Taco {

    @Id
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min=3, message="Name must be at least 3 characters long")
    private String nameTaco;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")

    private List<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

}
