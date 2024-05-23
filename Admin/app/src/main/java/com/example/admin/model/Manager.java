package com.example.admin.model;

public class Manager extends Account {

    private String name;

    public Manager() {
    }

    public Manager(String email, String password, String name) {
        super(email, password);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
