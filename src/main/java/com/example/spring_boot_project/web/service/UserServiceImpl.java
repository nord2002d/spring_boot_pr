package com.example.spring_boot_project.web.service;

import com.example.spring_boot_project.web.dao.UserDaoImpl;
import com.example.spring_boot_project.web.exeptions.UserNotFoundException;
import com.example.spring_boot_project.web.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDaoImpl userDaoImp;

    public UserServiceImpl(UserDaoImpl userDaoImp) {
        this.userDaoImp = userDaoImp;
    }
    @Transactional
    @Override
    public void add(User user) {
        try {
            userDaoImp.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Transactional
    @Override
    public List<User> listUsers() {
        try {
            return userDaoImp.listUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
    @Transactional
    @Override
    public User getUser(long id) throws UserNotFoundException {

        return userDaoImp.getUser(id);
    }
    @Transactional
    @Override
    public void removeUser(long id) throws UserNotFoundException {

        userDaoImp.removeUser(id);
    }
}
