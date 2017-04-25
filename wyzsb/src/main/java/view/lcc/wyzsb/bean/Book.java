package view.lcc.wyzsb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class Book implements Serializable{
    //mid
    private String id;
    //发布时间
    private String b_ct;
    //更新时间
    private String b_ut;
    //类别(是哪个类型的)
    private String b_type;
    //书籍的简单的介绍
    private String b_js;
    //点击数量
    private String b_c;
    //书的名字
    private String b_t;
    //书的购买地址的url
    private String b_u;
    //书的图片
    private String b_i;
    //书的评分
    private String b_g;

    //书的详情
    private String b_b;

    public String getB_b() {
        return b_b;
    }

    public void setB_b(String b_b) {
        this.b_b = b_b;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getB_ct() {
        return b_ct;
    }

    public void setB_ct(String b_ct) {
        this.b_ct = b_ct;
    }

    public String getB_ut() {
        return b_ut;
    }

    public void setB_ut(String b_ut) {
        this.b_ut = b_ut;
    }

    public String getB_type() {
        return b_type;
    }

    public void setB_type(String b_type) {
        this.b_type = b_type;
    }

    public String getB_js() {
        return b_js;
    }

    public void setB_js(String b_js) {
        this.b_js = b_js;
    }

    public String getB_c() {
        return b_c;
    }

    public void setB_c(String b_c) {
        this.b_c = b_c;
    }

    public String getB_t() {
        return b_t;
    }

    public void setB_t(String b_t) {
        this.b_t = b_t;
    }

    public String getB_u() {
        return b_u;
    }

    public void setB_u(String b_u) {
        this.b_u = b_u;
    }

    public String getB_i() {
        return b_i;
    }

    public void setB_i(String b_i) {
        this.b_i = b_i;
    }

    public String getB_g() {
        return b_g;
    }

    public void setB_g(String b_g) {
        this.b_g = b_g;
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
