package com.nju.training_college.vo;

public class NewInsVO {
    private String institutionName;

    private String mail;

    private String location;

    private String teachers;

    private String phone;

    public NewInsVO() {
    }

    public NewInsVO(String institutionName, String mail, String location, String teachers, String phone) {
        this.institutionName = institutionName;
        this.mail = mail;
        this.location = location;
        this.teachers = teachers;
        this.phone = phone;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
