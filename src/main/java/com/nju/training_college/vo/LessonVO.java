package com.nju.training_college.vo;

public class LessonVO {
    private String lessonName;

    private String startDay;

    private String endDay;

    private String lessonTime;

    private String teachers;

    private Integer classNum;

    private Integer stdNumPerClass;

    private Integer price;

    private String type;

    private String description;

    public LessonVO(){}

    public LessonVO(String lessonName, String startDay, String endDay, String lessonTime, String teachers, int classNum, int stdNumPerClass, int price, String type, String description) {
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

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
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
