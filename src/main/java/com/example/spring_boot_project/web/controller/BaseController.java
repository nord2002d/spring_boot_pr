package com.example.spring_boot_project.web.controller;

import com.example.spring_boot_project.web.model.User;
import com.example.spring_boot_project.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class BaseController {
    private static final String REDIRECT = "redirect:/";
    private UserService userService;

    public BaseController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String printWelcome(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("add", new User());
        model.addAttribute("delete", new User());
        model.addAttribute("update", new User());
        return "users";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute User user) {
        userService.add(user);
        return REDIRECT;
    }

    @PostMapping("/delete")
    public String removeUser(@ModelAttribute User user) {
        userService.removeUser(user.getId());
        return REDIRECT;
    }

    @PostMapping("/patch")
    public String updateUser(@ModelAttribute User user) {
        User updateUser = userService.getUser(user.getId());
        updateUser.setName(user.getName());
        updateUser.setSurName(user.getSurName());
        updateUser.setAge(user.getAge());
        userService.add(updateUser);
        return REDIRECT;
    }


}