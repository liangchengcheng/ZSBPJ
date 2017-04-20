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




    /**
     * _id : 58d49b13421aa93abb7d4e4c
     * createdAt : 2017-03-24T12:05:39.590Z
     * desc : Google guetzli JPEG 压缩的 Mac GUI 版本。
     * images : ["http://img.gank.io/41da45bd-b62b-4de7-bda1-fb628437aad2"]
     * publishedAt : 2017-03-24T12:12:34.753Z
     * source : chrome
     * type : iOS
     * url : https://github.com/daviesgeek/guetzli-gui
     * used : true
     * who : 密码
     */
    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
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
