package com.gibatekpro.sample_project.rest_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fun")
public class FunController {

    @GetMapping("/hello")
    public String hello() {

        return "Hello, again just testing some stuff again";
    }

}

