package com.cognizant.authapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/{id}")
    public String get(@PathVariable String id){
        return String.format("test with id: %s", id);
    }

    @GetMapping("/name/{name}")
    public String greet(@PathVariable String name){
        return String.format("Hello, %s", name);
    }
}
