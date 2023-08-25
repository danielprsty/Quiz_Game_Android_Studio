package com.niel24.quizgamebudayabetawi.Model;

public class User {
    private String name;
    private String nim;
    private String userName;
    private String password;

    public User() {
    }

    public User(String name, String nim, String userName, String password) {
        this.name = name;
        this.nim = nim;
        this.userName = userName;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

