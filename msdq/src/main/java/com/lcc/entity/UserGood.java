package com.lcc.entity;

import java.io.Serializable;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2016年07月19日21:39:06
 * Description:  UserGood（收藏的数据）
 */
public class UserGood implements Serializable {

    /**
     * id : 4
     * mid : 80cdcd1638bda699c004221558757893
     * title : 怎么成为注册会计师
     * author : 18813149871
     * gooder : 18813149871
     * nid : 46f337bddcb925c166bfac9acf96dea6
     * type : 面试感想
     * created_time : 16-06-12 11:50:44
     * updated_time : 16-06-12 11:50:44
     */

    private int id;
    private String mid;
    private String title;
    private String author;
    private String gooder;
    private String nid;
    private String type;
    private String created_time;
    private String updated_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGooder() {
        return gooder;
    }

    public void setGooder(String gooder) {
        this.gooder = gooder;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }
}
