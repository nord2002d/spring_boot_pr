package com.example.spring_boot_project.web.exeptions;

public class Violation {
    private final String fieldName;
    private final String message;

    public Violation(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Поле - %s %s",fieldName,message);
    }
}
