package com.restweb.merestfullweb.services;

import com.restweb.merestfullweb.models.Coffee;
import com.restweb.merestfullweb.repositories.CoffeeRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
    class DataLoader {
    private final CoffeeRepository coffeeRepository;

    public DataLoader(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @PostConstruct
    private void loadData() {
        coffeeRepository.saveAll(Stream.of(
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Três Pontas")).collect(Collectors.toList()));
    }
}