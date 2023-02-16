package com.gibatekpro.jackson_json_databinding.rest_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MainController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

}