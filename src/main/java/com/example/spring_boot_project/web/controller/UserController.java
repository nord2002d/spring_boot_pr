package com.example.spring_boot_project.web.controller;

import com.example.spring_boot_project.web.exeptions.UserNotFoundException;
import com.example.spring_boot_project.web.model.User;
import com.example.spring_boot_project.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller
public class UserController {

    private static final String REDIRECT = "redirect:/users";
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String showUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @GetMapping("/formAdd")
    public String getFormAddUser(ModelMap model) {
        model.addAttribute("add", new User());
        return "add";
    }

    @PostMapping
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("surName") String surName,
                          @RequestParam("age") int age) {
        User user = new User(name, surName, age);
        userService.add(user);
        return REDIRECT;
    }

    @DeleteMapping()
    public String removeUser(@RequestParam("id") long id) throws UserNotFoundException {
        userService.removeUser(id);
        return REDIRECT;
    }

    @GetMapping("/formUpdate")
    public String getFormUpdate(ModelMap model, @RequestParam("id") long id) throws UserNotFoundException {
        model.addAttribute("update", userService.getUser(id));
        return "update";
    }

    @PatchMapping()
    public String updateUser(@RequestParam("id") long id,
                             @RequestParam("name") String name,
                             @RequestParam("surName") String surName,
                             @RequestParam("age") int age) {
        User updateUser = new User(name, surName, age);
        updateUser.setId(id);
        userService.add(updateUser);
        return REDIRECT;
    }

}