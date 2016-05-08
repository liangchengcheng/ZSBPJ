package com.lcc.entity;

import java.io.Serializable;

/**
 * Created by lcc on 16/5/8.
 */
public class Result implements Serializable {

    /**
     * status : 3
     * message : 密码不正确
     * result : 111
     */

    private int status;
    private String message;
    private String result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
