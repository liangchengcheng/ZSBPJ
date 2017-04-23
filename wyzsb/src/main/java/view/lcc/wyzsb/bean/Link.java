package view.lcc.wyzsb.bean;

import java.io.Serializable;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class Link implements Serializable {
    //mid
    private String id;
    //发布时间
    private String l_ct;
    //更新时间
    private String l_ut;
    //类别(是   官网   还是  博客   还是论坛)
    private String l_type;
    //链接简单介绍
    private String l_js;
    //点击数量
    private String l_c;
    //链接的 主题
    private String l_t;
    //链接的url
    private String l_u;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getL_ct() {
        return l_ct;
    }

    public void setL_ct(String l_ct) {
        this.l_ct = l_ct;
    }

    public String getL_ut() {
        return l_ut;
    }

    public void setL_ut(String l_ut) {
        this.l_ut = l_ut;
    }

    public String getL_type() {
        return l_type;
    }

    public void setL_type(String l_type) {
        this.l_type = l_type;
    }

    public String getL_js() {
        return l_js;
    }

    public void setL_js(String l_js) {
        this.l_js = l_js;
    }

    public String getL_c() {
        return l_c;
    }

    public void setL_c(String l_c) {
        this.l_c = l_c;
    }

    public String getL_t() {
        return l_t;
    }

    public void setL_t(String l_t) {
        this.l_t = l_t;
    }

    public String getL_u() {
        return l_u;
    }

    public void setL_u(String l_u) {
        this.l_u = l_u;
    }

    private String name ;

    private String url ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
