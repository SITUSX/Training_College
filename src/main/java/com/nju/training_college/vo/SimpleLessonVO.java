package com.nju.training_college.vo;

public class SimpleLessonVO {
    private int lessonId;

    private String lessonName;

    private String description;

    public SimpleLessonVO(int lessonId, String lessonName, String description) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.description = description;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
