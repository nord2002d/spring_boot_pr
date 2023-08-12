package com.example.spring_boot_project.web.exeptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }

}
