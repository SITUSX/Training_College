package com.nju.training_college.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private int lessonId;

    @Column(name = "verify")
    private int verify;

    @Column(name = "lesson_name")
    private String lessonName;

    @Column(name = "start_day")
    private Date startDay;

    @Column(name = "end_day")
    private Date endDay;

    @Column(name = "lesson_time")
    private String lessonTime;

    private String teachers;

    @Column(name = "class_num")
    private int classNum;

    @Column(name = "stdnum_per_class")
    private int stdNumPerClass;

    private int price;

    private String type;

    private String description;

    public Lesson() {
    }

    public Lesson(int verify, String lessonName, Date startDay, Date endDay, String lessonTime, String teachers, int classNum, int stdNumPerClass, int price, String type, String description) {
        this.verify = verify;
        this.lessonName = lessonName;
        this.startDay = startDay;
        this.endDay = endDay;
        this.lessonTime = lessonTime;
        this.teachers = teachers;
        this.classNum = classNum;
        this.stdNumPerClass = stdNumPerClass;
        this.price = price;
        this.type = type;
        this.description = description;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public int getStdNumPerClass() {
        return stdNumPerClass;
    }

    public void setStdNumPerClass(int stdNumPerClass) {
        this.stdNumPerClass = stdNumPerClass;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
