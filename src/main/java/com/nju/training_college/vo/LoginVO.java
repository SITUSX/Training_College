package com.nju.training_college.vo;

public class LoginVO {

    private String mail;

    private String password;

    public LoginVO(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}