package com.nju.training_college.vo;

public class OrderVO {

    private int orderId;

    private int studentId;

    private String lessonName;

    private int classId;

    private int studentNum;

    private int totalPrice;

    private boolean isPaid;

    private boolean isCancel;

    private boolean isDrop;

    public OrderVO(int orderId, int studentId, String lessonName, int classId, int studentNum, int totalPrice, boolean isPaid, boolean isCancel, boolean isDrop) {
        this.orderId = orderId;
        this.studentId = studentId;
        this.lessonName = lessonName;
        this.classId = classId;
        this.studentNum = studentNum;
        this.totalPrice = totalPrice;
        this.isPaid = isPaid;
        this.isCancel = isCancel;
        this.isDrop = isDrop;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public boolean isDrop() {
        return isDrop;
    }

    public void setDrop(boolean drop) {
        isDrop = drop;
    }
}
