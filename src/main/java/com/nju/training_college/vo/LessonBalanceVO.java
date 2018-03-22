package com.nju.training_college.vo;

public class LessonBalanceVO {
    private int lessonId;

    private int balance;

    public LessonBalanceVO() {
    }

    public LessonBalanceVO(int lessonId, int balance) {
        this.lessonId = lessonId;
        this.balance = balance;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
