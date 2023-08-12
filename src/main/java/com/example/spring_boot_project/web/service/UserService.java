package com.example.spring_boot_project.web.service;

import com.example.spring_boot_project.web.exeptions.UserNotFoundException;
import com.example.spring_boot_project.web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUser(long id) throws UserNotFoundException;
    void removeUser(long id) throws UserNotFoundException;
}
