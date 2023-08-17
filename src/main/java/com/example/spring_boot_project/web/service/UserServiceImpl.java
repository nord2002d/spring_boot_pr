package com.example.spring_boot_project.web.service;

import com.example.spring_boot_project.web.dao.UserDaoImpl;
import com.example.spring_boot_project.web.exeptions.UserNotFoundException;
import com.example.spring_boot_project.web.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class UserServiceImpl implements UserService {

    private UserDaoImpl userDaoImp;

    public UserServiceImpl(UserDaoImpl userDaoImp) {
        this.userDaoImp = userDaoImp;
    }

    @Transactional
    @Override
    public void add(@Valid User user) throws ConstraintViolationException {
        try {
            userDaoImp.add(user);
        } catch (ConstraintViolationException ex) {
            throw ex;
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
        Optional<User> user = Optional.empty();
        try {
            user = Optional.ofNullable(userDaoImp.getUser(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.orElseThrow(() ->
                new UserNotFoundException(String.format("Пользователь с таки id - %d не найден", id)));
    }
    @Transactional
    @Override
    public void removeUser(long id) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(userDaoImp.getUser(id));
        if (user.isPresent()) {
            userDaoImp.removeUser(user.get());
        } else {
            throw new UserNotFoundException(String.format("Пользователь с таки id - %d не найден", id));
        }
    }
}
