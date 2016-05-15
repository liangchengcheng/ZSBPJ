package com.lcc.entity;

import java.io.Serializable;

public class TestEntity implements Serializable {


    /**
     * id : 1
     * mid : 775c8460706676357b8e8ba8e164350b
     * category : 程序员
     * type : javaweb开发
     * options : 0
     * title : qqwqwqwq
     * summary :
     * created_time : 16-05-12 01:46:24
     * updated_time : 16-05-12 01:46:24
     * author : 18813149871
     * fnumber : 0
     * lnumber : 0
     * source : wqwq
     * keyword : wq
     * ok : 已下架
     * userinfo : {"id":1,"phone":"18813149871","nickname":"xiaochengcheng","xb":"男","email":"1038127753@qq.com","created_at":"2016年05月07日12:53:37","jf":"10","qm":"人生自古谁不死啊","zy":"程序员","user_image":"http://h.hiphotos.baidu.com/image/h%3D200/sign=71cd4229be014a909e3e41bd99763971/472309f7905298221dd4c458d0ca7bcb0b46d442.jpg"}
     */

    private int id;
    private String mid;
    private String category;
    private String type;
    private String options;
    private String title;
    private String summary;
    private String created_time;
    private String updated_time;
    private String author;
    private String fnumber;
    private String lnumber;
    private String source;
    private String keyword;
    private String ok;
    /**
     * id : 1
     * phone : 18813149871
     * nickname : xiaochengcheng
     * xb : 男
     * email : 1038127753@qq.com
     * created_at : 2016年05月07日12:53:37
     * jf : 10
     * qm : 人生自古谁不死啊
     * zy : 程序员
     * user_image : http://h.hiphotos.baidu.com/image/h%3D200/sign=71cd4229be014a909e3e41bd99763971/472309f7905298221dd4c458d0ca7bcb0b46d442.jpg
     */

    private UserinfoEntity userinfo;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFnumber() {
        return fnumber;
    }

    public void setFnumber(String fnumber) {
        this.fnumber = fnumber;
    }

    public String getLnumber() {
        return lnumber;
    }

    public void setLnumber(String lnumber) {
        this.lnumber = lnumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public UserinfoEntity getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoEntity userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoEntity {
        private int id;
        private String phone;
        private String nickname;
        private String xb;
        private String email;
        private String created_at;
        private String jf;
        private String qm;
        private String zy;
        private String user_image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getJf() {
            return jf;
        }

        public void setJf(String jf) {
            this.jf = jf;
        }

        public String getQm() {
            return qm;
        }

        public void setQm(String qm) {
            this.qm = qm;
        }

        public String getZy() {
            return zy;
        }

        public void setZy(String zy) {
            this.zy = zy;
        }

        public String getUser_image() {
            return user_image;
        }

        public void setUser_image(String user_image) {
            this.user_image = user_image;
        }
    }
}
