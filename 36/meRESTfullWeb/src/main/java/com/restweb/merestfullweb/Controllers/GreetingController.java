package com.restweb.merestfullweb.Controllers;

import com.restweb.merestfullweb.models.Greeting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final Greeting greeting;

//    @Value("${greeting-name: Nobody}")
//    private String name;
//
//    @Value("${greeting-coffee}")
//    private String coffee;

    public GreetingController(Greeting greeting) {
        this.greeting = greeting;
    }

    @GetMapping
    public String getGreeting(){
        return greeting.getName();
    }

    @GetMapping("/coffee")
    public String getNameandCoffee(){
        return greeting.getCoffee();
    }


}
