package com.example.spring_boot_project.web.dao;

import com.example.spring_boot_project.web.exeptions.UserNotFoundException;
import com.example.spring_boot_project.web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user) throws Exception;
    List<User> listUsers();
    User getUser(long id) throws UserNotFoundException;
    void removeUser(User user) throws UserNotFoundException;
}
