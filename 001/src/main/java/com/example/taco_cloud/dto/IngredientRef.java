package com.example.taco_cloud.dto;

public class IngredientRef {

    private final String ingredient;
    private Integer taco;
    private Integer taco_key;

    public IngredientRef(String ingredient, Integer taco, Integer taco_key) {
        this.ingredient = ingredient;
        this.taco = taco;
        this.taco_key = taco_key;
    }

    public String getIngredient() {
        return ingredient;
    }


    public Integer getTaco() {
        return taco;
    }

    public void setTaco(Integer taco) {
        this.taco = taco;
    }

    public Integer getTaco_key() {
        return taco_key;
    }

    public void setTaco_key(Integer taco_key) {
        this.taco_key = taco_key;
    }

    @Override
    public String toString() {
        return "IngredientRef{" +
                "ingredient='" + ingredient + '\'' +
                ", taco=" + taco +
                ", taco_key=" + taco_key +
                '}';
    }


}
