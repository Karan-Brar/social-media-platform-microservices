package com.example.postservice.misc;

public class ErrorResponse {
    private String message;

    public ErrorResponse(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(){
        this.message = message;
    }
}

