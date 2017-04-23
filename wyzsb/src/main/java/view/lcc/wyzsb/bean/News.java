package view.lcc.wyzsb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class News implements Serializable{
    //mid 新闻的主键
    private String id;
    //预览图
    private String n_img;
    //发布时间
    private String n_ct;
    //更新时间
    private String n_ut;
    //新闻主题
    private String n_t;
    //新闻来源
    private String n_s;
    //作者
    private String n_a;
    //类别
    private String n_type;
    //新闻简单介绍
    private String n_js;
    //点击数量
    private String n_c;
    //新闻的正文
    private String n_b;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getN_img() {
        return n_img;
    }

    public void setN_img(String n_img) {
        this.n_img = n_img;
    }

    public String getN_ct() {
        return n_ct;
    }

    public void setN_ct(String n_ct) {
        this.n_ct = n_ct;
    }

    public String getN_ut() {
        return n_ut;
    }

    public void setN_ut(String n_ut) {
        this.n_ut = n_ut;
    }

    public String getN_t() {
        return n_t;
    }

    public void setN_t(String n_t) {
        this.n_t = n_t;
    }

    public String getN_s() {
        return n_s;
    }

    public void setN_s(String n_s) {
        this.n_s = n_s;
    }

    public String getN_a() {
        return n_a;
    }

    public void setN_a(String n_a) {
        this.n_a = n_a;
    }

    public String getN_type() {
        return n_type;
    }

    public void setN_type(String n_type) {
        this.n_type = n_type;
    }

    public String getN_js() {
        return n_js;
    }

    public void setN_js(String n_js) {
        this.n_js = n_js;
    }

    public String getN_c() {
        return n_c;
    }

    public void setN_c(String n_c) {
        this.n_c = n_c;
    }

    public String getN_b() {
        return n_b;
    }

    public void setN_b(String n_b) {
        this.n_b = n_b;
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
