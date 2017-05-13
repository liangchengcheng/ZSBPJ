package com.hsfcompany.tzcs.bean;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-13 16:39
 * Description:  |
 */
public class News implements Serializable {

    private String title;

    private String des;

    private int rid;

    private String what;
    private String what_content;
    private String jili;
    private String jili_content;
    private String yingxiang;
    private String yingxiang_content;
    private String tiaoli;
    private String tiaoli_content;

    public String getWhat_content() {
        return what_content;
    }

    public void setWhat_content(String what_content) {
        this.what_content = what_content;
    }

    public String getJili_content() {
        return jili_content;
    }

    public void setJili_content(String jili_content) {
        this.jili_content = jili_content;
    }

    public String getYingxiang_content() {
        return yingxiang_content;
    }

    public void setYingxiang_content(String yingxiang_content) {
        this.yingxiang_content = yingxiang_content;
    }

    public String getTiaoli_content() {
        return tiaoli_content;
    }

    public void setTiaoli_content(String tiaoli_content) {
        this.tiaoli_content = tiaoli_content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getJili() {
        return jili;
    }

    public void setJili(String jili) {
        this.jili = jili;
    }

    public String getYingxiang() {
        return yingxiang;
    }

    public void setYingxiang(String yingxiang) {
        this.yingxiang = yingxiang;
    }

    public String getTiaoli() {
        return tiaoli;
    }

    public void setTiaoli(String tiaoli) {
        this.tiaoli = tiaoli;
    }
}
