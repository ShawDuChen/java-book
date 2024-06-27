package com.utils;
import com.opensymphony.xwork2.ActionSupport;

import java.lang.reflect.ParameterizedType;

public class BaseActionSupport<T> extends ActionSupport {
    private int code;
    private String message;
    private T data;
    private Class clazz;

    public BaseActionSupport() {
        try {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            this.clazz = (Class) type.getActualTypeArguments()[0];
            this.data = (T) this.clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void actionSuccess(T data) {
        setData(data);
        setCode(200);
        setMessage(SUCCESS);
    }

    public void actionError() {
        setData(null);
        setCode(500);
        setMessage(ERROR);
    }
}
