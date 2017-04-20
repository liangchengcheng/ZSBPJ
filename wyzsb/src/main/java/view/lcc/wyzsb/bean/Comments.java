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
