package com.example.exceptions;

public class BuyerNotFoundException extends Exception{
    public BuyerNotFoundException(String message) {
        super(message);
    }
}
