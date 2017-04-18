package view.lcc.wyzsb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class Video implements Serializable{

    //mid
    private String id;
    //题目
    private String v_t;
    //作者
    private String v_a;
    //预览图
    private String v_img;
    //发布时间
    private String v_ct;
    //更新时间
    private String v_ut;
    //视频地址
    private String v_url;
    //视频时间
    private String v_time;
    //类别
    private String v_type;
    //视频简单介绍
    private String v_js;
    //点击数量
    private String v_c;
    //等级(初级，中级，高级)
    private String v_l;


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
