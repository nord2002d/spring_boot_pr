package com.example.spring_boot_project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DefaultController {
    @GetMapping(value = "/")
    public String printWelcome() {
        return "hello";
    }
}
