package com.example.taco_cloud;

import com.example.taco_cloud.data.IngredientRepository;
import com.example.taco_cloud.dto.Ingredient;
import org.h2.tools.Server;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.SQLException;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);

    }

//    todo ==> performs an update
//    @Bean
//    public ApplicationRunner dataLoader(IngredientRepository repo) {
//        return args -> {
//            repo.save(new Ingredient("FLTO", "Flour1 Tortilla", Ingredient.Type.WRAP));
//            repo.save(new Ingredient("COTO", "Corn1 Tortilla", Ingredient.Type.WRAP));
//        };
//    }
}


