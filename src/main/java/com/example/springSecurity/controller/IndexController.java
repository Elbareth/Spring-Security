package com.example.springSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping(value = {"/", "/index"})
    public String basic(){
        return "Witaj na stronie głównej";
    }
    @GetMapping("/courses")
    public String tmp(){
        return "Witaj w świecie kursów";
    }
}
