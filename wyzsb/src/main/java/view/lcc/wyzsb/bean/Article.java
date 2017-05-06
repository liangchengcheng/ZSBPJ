package view.lcc.wyzsb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class Article implements Serializable{

    private String bq;

    public String getBq() {
        return bq;
    }

    public void setBq(String bq) {
        this.bq = bq;
    }

    //mid 文章的主键
    private String id;
    //文章的题目
    private String a_t;
    //作者
    private String a_a;
    //预览图
    private String a_img;
    //发布时间
    private String a_ct;
    //更新时间
    private String a_ut;
    //文章的主体
    private String a_b;
    //视频时间
    private String a_time;
    //类别
    private String a_type;
    //文章的简单介绍
    private String a_js;
    //点击数量
    private String a_c;
    //等级(初级，中级，高级)
    private String a_l;

    private String a_id;

    private String a_hi;

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public String getA_hi() {
        return a_hi;
    }

    public void setA_hi(String a_hi) {
        this.a_hi = a_hi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getA_t() {
        return a_t;
    }

    public void setA_t(String a_t) {
        this.a_t = a_t;
    }

    public String getA_a() {
        return a_a;
    }

    public void setA_a(String a_a) {
        this.a_a = a_a;
    }

    public String getA_img() {
        return a_img;
    }

    public void setA_img(String a_img) {
        this.a_img = a_img;
    }

    public String getA_ct() {
        return a_ct;
    }

    public void setA_ct(String a_ct) {
        this.a_ct = a_ct;
    }

    public String getA_ut() {
        return a_ut;
    }

    public void setA_ut(String a_ut) {
        this.a_ut = a_ut;
    }

    public String getA_b() {
        return a_b;
    }

    public void setA_b(String a_b) {
        this.a_b = a_b;
    }

    public String getA_time() {
        return a_time;
    }

    public void setA_time(String a_time) {
        this.a_time = a_time;
    }

    public String getA_type() {
        return a_type;
    }

    public void setA_type(String a_type) {
        this.a_type = a_type;
    }

    public String getA_js() {
        return a_js;
    }

    public void setA_js(String a_js) {
        this.a_js = a_js;
    }

    public String getA_c() {
        return a_c;
    }

    public void setA_c(String a_c) {
        this.a_c = a_c;
    }

    public String getA_l() {
        return a_l;
    }

    public void setA_l(String a_l) {
        this.a_l = a_l;
    }

    // 暂无数据属性
    private boolean isNoData = false;
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isNoData() {
        return isNoData;
    }

    public void setNoData(boolean noData) {
        isNoData = noData;
    }
}
