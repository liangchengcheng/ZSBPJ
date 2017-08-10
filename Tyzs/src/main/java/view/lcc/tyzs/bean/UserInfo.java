package view.lcc.tyzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-10 22:23
 * Description:  |
 */
@Entity(nameInDb = "USER_INFO", createInDb = false)
public class UserInfo implements Serializable {
    static final long serialVersionUID = 13L;
    @Id
    @Property(nameInDb = "id")
    private Long id;
    private String nickname;
    private String sex;
    private String ctime;
    private Integer tanshizhi;
    private Integer yangxuzhi;
    private Integer yinxuzhi;
    private Integer qiyuzhi;
    private Integer shirezhi;
    private Integer qixuzhi;
    private Integer xueyuzhi;
    private Integer tebingzhi;




    @Generated(hash = 1388835576)
    public UserInfo(Long id, String nickname, String sex, String ctime,
            Integer tanshizhi, Integer yangxuzhi, Integer yinxuzhi, Integer qiyuzhi,
            Integer shirezhi, Integer qixuzhi, Integer xueyuzhi,
            Integer tebingzhi) {
        this.id = id;
        this.nickname = nickname;
        this.sex = sex;
        this.ctime = ctime;
        this.tanshizhi = tanshizhi;
        this.yangxuzhi = yangxuzhi;
        this.yinxuzhi = yinxuzhi;
        this.qiyuzhi = qiyuzhi;
        this.shirezhi = shirezhi;
        this.qixuzhi = qixuzhi;
        this.xueyuzhi = xueyuzhi;
        this.tebingzhi = tebingzhi;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }



    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public Integer getTanshizhi() {
        return tanshizhi;
    }

    public void setTanshizhi(Integer tanshizhi) {
        this.tanshizhi = tanshizhi;
    }

    public Integer getYangxuzhi() {
        return yangxuzhi;
    }

    public void setYangxuzhi(Integer yangxuzhi) {
        this.yangxuzhi = yangxuzhi;
    }

    public Integer getYinxuzhi() {
        return yinxuzhi;
    }

    public void setYinxuzhi(Integer yinxuzhi) {
        this.yinxuzhi = yinxuzhi;
    }

    public Integer getQiyuzhi() {
        return qiyuzhi;
    }

    public void setQiyuzhi(Integer qiyuzhi) {
        this.qiyuzhi = qiyuzhi;
    }

    public Integer getShirezhi() {
        return shirezhi;
    }

    public void setShirezhi(Integer shirezhi) {
        this.shirezhi = shirezhi;
    }

    public Integer getQixuzhi() {
        return qixuzhi;
    }

    public void setQixuzhi(Integer qixuzhi) {
        this.qixuzhi = qixuzhi;
    }

    public Integer getXueyuzhi() {
        return xueyuzhi;
    }

    public void setXueyuzhi(Integer xueyuzhi) {
        this.xueyuzhi = xueyuzhi;
    }

    public Integer getTebingzhi() {
        return tebingzhi;
    }

    public void setTebingzhi(Integer tebingzhi) {
        this.tebingzhi = tebingzhi;
    }
}
