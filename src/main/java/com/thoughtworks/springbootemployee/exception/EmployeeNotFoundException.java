package com.thoughtworks.springbootemployee.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public EmployeeNotFoundException(String message){
        this.message = message;
    }
}
