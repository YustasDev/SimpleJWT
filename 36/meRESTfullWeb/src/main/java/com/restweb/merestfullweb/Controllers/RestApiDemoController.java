package com.restweb.merestfullweb.Controllers;

import com.restweb.merestfullweb.models.Coffee;
import com.restweb.merestfullweb.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
    @RequestMapping("/coffees")
    class RestApiDemoController {
    @Autowired
    private final CoffeeRepository coffeeRepository;

    RestApiDemoController(CoffeeRepository coffeeRepository) {
            this.coffeeRepository = coffeeRepository;
            this.coffeeRepository.saveAll(Stream.of(
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Três Pontas")).collect(Collectors.toList()));
        }

    @GetMapping
    Iterable<Coffee> getCoffees() {
        //return coffees;
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
//        for (Coffee c: coffees) {
//            if (c.getId().equals(id)) {
//                return Optional.of(c);
//            }
//        }
//        return Optional.empty();
        return coffeeRepository.findById(id);
        }

    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee) {
//        coffees.add(coffee);
//        return coffee;
        return coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
//        int coffeeIndex = -1;
//        for (Coffee c: coffees) {
//            if (c.getId().equals(id)) {
//                coffeeIndex = coffees.indexOf(c);
//                coffees.set(coffeeIndex, coffee);
//            }
//        }
//        return (coffeeIndex == -1) ?
//                new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
//                new ResponseEntity<>(coffee, HttpStatus.OK);

        return (!coffeeRepository.existsById(id)) ? new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED)
                : new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
       // coffees.removeIf(c -> c.getId().equals(id));
        coffeeRepository.deleteById(id);
    }

}

