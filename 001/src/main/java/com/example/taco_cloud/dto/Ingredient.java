package com.example.taco_cloud.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;


    @Data
    @Table
    @AllArgsConstructor
    @NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
    public class Ingredient implements Persistable<String> {


    @Id
    private String id;
    private final String nameingredient;
    private final Type typeingredient;




    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", nameingredient='" + nameingredient + '\'' +
                ", typeingredient=" + typeingredient +
                '}';
    }

    @Override
    public boolean isNew() {
        return false;
    }


    public enum Type {
            WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
        }
    }

