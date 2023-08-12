package com.example.spring_boot_project.web.service;

import com.example.spring_boot_project.web.exeptions.UserNotFoundException;
import com.example.spring_boot_project.web.model.User;
import com.example.spring_boot_project.web.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> listUsers() {
        try {
            Iterable<User> users = userRepository.findAll();
            List<User> result = new ArrayList<>();
            users.forEach(result::add);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public User getUser(long id) throws UserNotFoundException {
        Optional<User> user = Optional.empty();
        try {
            user = userRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.orElseThrow(() -> new UserNotFoundException("Пользователь с таким id = " + id + ", не найден"));
    }

    @Override
    public void removeUser(long id) throws UserNotFoundException {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(
                    "В процессе удаления возникла ошибка: пользователь с таким id = " + id + ", не найден");
        }
    }
}
