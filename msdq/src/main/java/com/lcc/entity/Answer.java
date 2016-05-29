package com.lcc.entity;

import java.io.Serializable;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  开始或者结束轮训器
 */
public class Answer implements Serializable {

    /**
     * id : 1
     * created_time : 16-05-12 03:23:04
     * updated_time : 16-05-12 03:23:04
     * author : 18813149801
     * answer : 上篇文章介绍了 syscall 来打通 Android 上层与 kernel 底层，今天再跟大家分享 JNI 打通 Android 上层的 Java 层跟 C/C++ 层的桥梁。上篇文章介绍了 syscall 来打通 Android 上层与 kernel 底层，今天再跟大家分享 JNI 打通 Android 上层的 Java 层跟 C/C++ 层的桥梁。
     * gnumber : 0
     * fid : 5e7f684866bee25219269994f4784573
     * mid : 0ed3760a334959a38431b90fee7786f8
     * ok : 0
     * userinfo : {"id":2,"phone":"18813149801","nickname":"xiaochengcheng","xb":"男","email":"1038127753@qq.com","created_at":"2016年05月06日22:45:46","jf":"10","qm":"人生自古谁无死","zy":"程序员","user_image":"http://ww1.sinaimg.cn/crop.7.22.1192.1192.1024/5c6defebjw8epti0r9noaj20xc0y1n0x.jpg"}
     */

    private int id;
    private String created_time;
    private String updated_time;
    private String author;
    private String answer;
    private String gnumber;
    private String fid;
    private String mid;
    private String ok;
    /**
     * id : 2
     * phone : 18813149801
     * nickname : xiaochengcheng
     * xb : 男
     * email : 1038127753@qq.com
     * created_at : 2016年05月06日22:45:46
     * jf : 10
     * qm : 人生自古谁无死
     * zy : 程序员
     * user_image : http://ww1.sinaimg.cn/crop.7.22.1192.1192.1024/5c6defebjw8epti0r9noaj20xc0y1n0x.jpg
     */

    private UserinfoEntity userinfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getGnumber() {
        return gnumber;
    }

    public void setGnumber(String gnumber) {
        this.gnumber = gnumber;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public static class UserinfoEntity implements Serializable{
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
