package com.nju.training_college.util;

public class ResponseData<T> {

    private boolean result;

    private String message;

    private T data;

    public ResponseData() {}

    public ResponseData(boolean result){
        this.result = result;
        this.message = null;
        this.data = null;
    }

    public ResponseData(boolean result, String message){
        this.result = result;
        this.message = message;
        this.data = null;
    }

    public ResponseData(boolean result, String message, T data){
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
