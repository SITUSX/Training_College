package com.nju.training_college.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StdInfoVO {
    private int studentId;

    private String mail;

    private String stdName;

    private boolean isMember;

    private int lvMember;

    private int consumption;

    public StdInfoVO(int studentId, String mail, String stdName, boolean isMember, int lvMember, int consumption) {
        this.studentId = studentId;
        this.mail = mail;
        this.stdName = stdName;
        this.isMember = isMember;
        this.lvMember = lvMember;
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

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }
}
