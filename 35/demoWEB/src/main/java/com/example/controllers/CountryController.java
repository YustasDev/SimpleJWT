package com.example.controllers;

import com.example.models.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
    public class CountryController {

    @GetMapping("/france")
    public ResponseEntity<Country> france() {
        Country c = Country.of("France", 67);

        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .header("continent", "Europe")
                .header("capital", "Paris")
                .header("favorite_food", "cheese and wine")
                .body(c);
    }


        @GetMapping("/all")
        public List<Country> countries() {
            List<Country> listCountry = new ArrayList<>();

            Country c1 = Country.of("France", 67);
            Country c2 = Country.of("Spain", 47);

            listCountry.add(c1);
            listCountry.add(c2);

            return listCountry;
        }
    }
