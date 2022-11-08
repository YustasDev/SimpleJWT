package com.example.taco_cloud.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Taco {

    @NotNull
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    public Taco() {
    }


    public Taco(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Taco{ " +
                " name= '" + name + '\'' +
                ", ingredients= " + ingredients +
                '}';
    }



}
