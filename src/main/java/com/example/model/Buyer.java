package com.example.model;

public class Buyer {

    private int id;
    private String name;
    private String surname;
    private String email;
    private double balance;

    public Buyer(int id, String name, String surname, String email, double balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.balance = balance;
    }

    public Buyer() {

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
