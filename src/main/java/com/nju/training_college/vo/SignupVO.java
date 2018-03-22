package com.nju.training_college.vo;

public class SignupVO {
    private String mail;

    private String password;

    private String stdName;

    public SignupVO(String mail, String password, String stdName){
        this.mail = mail;
        this.password = password;
        this.stdName = stdName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getStdName() {
        return stdName;
    }
}
