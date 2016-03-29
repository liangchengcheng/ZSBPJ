package com.lcc.entity;

import java.io.Serializable;

// TODO: 16/3/13 因为每个bean都放在里面了，所以要加标识判断 ，否则每个activity或者fragment的接收都会强制解析那个 T
public class ResultEntity<T> implements Serializable {

    private int state;

    private int code;

    private int class_tag;

    public int getClass_tag() {
        return class_tag;
    }

    public void setClass_tag(int class_tag) {
        this.class_tag = class_tag;
    }

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
