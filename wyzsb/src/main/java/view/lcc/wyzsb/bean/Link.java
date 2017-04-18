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
    private String n_ct;
    //更新时间
    private String n_ut;
    //类别(是   官网   还是  博客   还是论坛)
    private String v_type;
    //链接简单介绍
    private String l_js;
    //点击数量
    private String l_c;
    //链接的 主题
    private String v_t;
    //链接的url
    private String v_u;







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
