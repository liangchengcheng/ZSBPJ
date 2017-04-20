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
