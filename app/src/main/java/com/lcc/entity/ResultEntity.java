package com.lcc.entity;

import java.io.Serializable;

/**
 * Created by lcc on 16/3/13.
 */
public class ResultEntity<T> implements Serializable {

    private int state;

    private int code;

    private T t;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
