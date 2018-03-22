package com.nju.training_college.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "student")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler","password"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    private String mail;

    @Column(name = "stdname")
    private String stdName;

    //not safe
    private String password;

    @Column(name = "is_member")
    private boolean isMember;

    @Column(name = "lv_member")
    private int lvMember;

    private int consumption;

    //default constructor
    public Student(){}

    //initialize when sign up
    public Student(String mail, String password, String stdName){
        this.mail = mail;
        this.password = password;
        this.stdName = stdName;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty(value = "isMember")
    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public int getLvMember() {
        return lvMember;
    }

    public void setLvMember(int lvMember) {
        this.lvMember = lvMember;
    }
}
