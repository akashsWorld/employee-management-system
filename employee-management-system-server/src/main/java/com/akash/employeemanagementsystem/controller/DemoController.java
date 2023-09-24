package com.akash.employeemanagementsystem.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/demo")
@RestController
public class DemoController {

    @GetMapping
    public String demoController(){
        return "Hello this is a secured point";
    }
}
