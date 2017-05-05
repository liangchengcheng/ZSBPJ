package view.lcc.wyzsb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class Comments implements Serializable{

    // (这个评论是视频的评论，文章的评论暂时没写)
    // 这个评论的id
    private String id;
    // 评论的内容
    private String c_b;
    // 评论人的id
    private String c_a;
    // 评论人的昵称
    private String c_nn;
    // 评论人的头像
    private String c_im;
    // 评论的时间
    private String c_t;
    // 评论的类型
    private String c_tp;
    // 这个评论属于那个作品 比例视频的id
    private String o_id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getC_b() {
        return c_b;
    }

    public void setC_b(String c_b) {
        this.c_b = c_b;
    }

    public String getC_a() {
        return c_a;
    }

    public void setC_a(String c_a) {
        this.c_a = c_a;
    }

    public String getC_nn() {
        return c_nn;
    }

    public void setC_nn(String c_nn) {
        this.c_nn = c_nn;
    }

    public String getC_im() {
        return c_im;
    }

    public void setC_im(String c_im) {
        this.c_im = c_im;
    }

    public String getC_t() {
        return c_t;
    }

    public void setC_t(String c_t) {
        this.c_t = c_t;
    }

    public String getC_tp() {
        return c_tp;
    }

    public void setC_tp(String c_tp) {
        this.c_tp = c_tp;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
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
