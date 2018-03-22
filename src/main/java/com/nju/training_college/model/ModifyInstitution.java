package com.nju.training_college.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.bind.annotation.CookieValue;

import javax.persistence.*;

@Entity
@Table(name = "modify_institution")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class ModifyInstitution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "institution_id")
    private int institutionId;

    private int verify;

    private String password;

    private String mail;

    @Column(name = "institution_name")
    private String institutionName;

    private String location;

    private String teachers;

    private String phone;

    public ModifyInstitution() {
    }

    public ModifyInstitution(int institutionId, int verify, String password, String mail, String institutionName, String location, String teachers, String phone) {
        this.institutionId = institutionId;
        this.verify = verify;
        this.password = password;
        this.mail = mail;
        this.institutionName = institutionName;
        this.location = location;
        this.teachers = teachers;
        this.phone = phone;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
