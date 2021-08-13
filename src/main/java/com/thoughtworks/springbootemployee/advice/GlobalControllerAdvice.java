package com.thoughtworks.springbootemployee.advice;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorReponse employeeNotFoundExceptionHandling (EmployeeNotFoundException employeeNotFoundException){
    return new ErrorReponse(employeeNotFoundException.getMessage());
    }
}
