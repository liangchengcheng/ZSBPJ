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
