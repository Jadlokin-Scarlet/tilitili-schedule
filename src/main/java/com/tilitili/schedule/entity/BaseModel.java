package com.tilitili.schedule.entity;

import java.util.List;

public class BaseModel<T> {
    private String message;
    private Boolean success;
    private T data;
    private List<T> list;

    public BaseModel(String message) {
        this.message = message;
        this.success = false;
    }

    public BaseModel(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public BaseModel(String message, boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public BaseModel(String message, boolean success, List<T> list) {
        this.message = message;
        this.success = success;
        this.list = list;
    }

    public static <T> BaseModel<T> success(List<T> list) {
        return new BaseModel<>("成功", true, list);
    }

    public static <T> BaseModel<T> success(T data) {
        return new BaseModel<>("成功", true, data);
    }

    public static <T> BaseModel<T> success() {
        return new BaseModel<>("成功", true);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
