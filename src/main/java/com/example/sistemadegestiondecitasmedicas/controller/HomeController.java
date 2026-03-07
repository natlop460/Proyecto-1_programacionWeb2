package com.example.sistemadegestiondecitasmedicas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String home(){
        return "dashboard";
    }
}