package com.example.exceptions;

public class InsufficientQuantityException extends Exception{
    public InsufficientQuantityException(String message) {
        super(message);
    }
}
