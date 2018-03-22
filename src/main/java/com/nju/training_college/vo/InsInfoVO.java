package com.nju.training_college.vo;

public class InsInfoVO {
    private int institutionId;

    private int verify;

    private String mail;

    private String institutionName;

    private String location;

    private String teachers;

    private String phone;

    public InsInfoVO() { }

    public InsInfoVO(int institutionId, int verify, String mail, String institutionName, String location, String teachers, String phone) {
        this.institutionId = institutionId;
        this.verify = verify;
        this.mail = mail;
        this.institutionName = institutionName;
        this.location = location;
        this.teachers = teachers;
        this.phone = phone;
    }

    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
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
