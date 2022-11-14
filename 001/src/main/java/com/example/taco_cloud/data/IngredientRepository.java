package com.example.taco_cloud.data;

import com.example.taco_cloud.dto.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}



