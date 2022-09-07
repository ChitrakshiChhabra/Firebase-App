package com.example.firebaseapplication;

public class message {
    String name, message;

    public message(String name, String message)
    {
        this.name = name;
        this.message = message;
    }

    public message() {

    }

    public String getName()
    {
        return name;
    }
    public void setName()
    {
        this.name = name;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}

