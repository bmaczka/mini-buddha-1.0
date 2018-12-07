package com.example.blake.minibuddhav0;

public class User {
    public static String name;

    public User() {
        this.name = "";
    }

    public User(String user){
        this.name = user;
    }

    public String getUser() {
        return this.name;
    }

    public void setUser(String input) {
        this.name = input;
    }



}
