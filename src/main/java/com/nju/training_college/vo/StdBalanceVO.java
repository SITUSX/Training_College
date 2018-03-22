package com.nju.training_college.vo;

public class StdBalanceVO {
    private int studentId;

    private int balance;

    public StdBalanceVO(int studentId, int balance) {
        this.studentId = studentId;
        this.balance = balance;
    }

    public StdBalanceVO() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
