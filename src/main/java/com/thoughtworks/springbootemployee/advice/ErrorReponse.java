package com.thoughtworks.springbootemployee.advice;

public class ErrorReponse {


    private String message;

    public ErrorReponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
