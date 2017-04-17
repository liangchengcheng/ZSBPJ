package view.lcc.wyzsb.bean.model;

import java.io.Serializable;

public class TravelingEntity implements Serializable{




    // 暂无数据属性
    private boolean isNoData = false;
    private int height;
    /**
     * id : 4
     * mid : 1cddd741560e7d90ebf9112b989ba955
     * company_name : 华为科技网络有限公司
     * company_image : https://ss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/70d854e736d12f2e73293d5347c2d5628535688d.jpg
     * company_phone : 886868768767
     * company_description : 是一家生产销售通信设备的民营通信科技公司，总部位于中国广东省深圳市龙岗区坂田华为基地。华为的产品主要涉及通信网络中的交换网络、传输网络、无线及有线固定接入网络和数据通信网络及无线终端产品，为世界各地通信运营商及专业网络拥有者提供硬件设备、软件、服务和解决方案。华为于1987年在中国深圳正式注册成立。
     * author : 18813149871
     * location : 山东省威海市环翠区
     * province : 河北省
     * city : 石家庄市
     * area : 市辖区
     * areaid :
     * l_num : 0
     * z_num : 0
     * c_num : 11
     * question_count : 3
     */

    private String id;
    private String mid;
    private String company_name;
    private String company_image;
    private String company_phone;
    private String company_description;
    private String author;
    private String location;
    private String province;
    private String city;
    private String area;
    private String areaid;
    private String l_num;
    private String z_num;
    private String c_num;
    private String question_count;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_image() {
        return company_image;
    }

    public void setCompany_image(String company_image) {
        this.company_image = company_image;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public String getCompany_description() {
        return company_description;
    }

    public void setCompany_description(String company_description) {
        this.company_description = company_description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getL_num() {
        return l_num;
    }

    public void setL_num(String l_num) {
        this.l_num = l_num;
    }

    public String getZ_num() {
        return z_num;
    }

    public void setZ_num(String z_num) {
        this.z_num = z_num;
    }

    public String getC_num() {
        return c_num;
    }

    public void setC_num(String c_num) {
        this.c_num = c_num;
    }

    public String getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(String question_count) {
        this.question_count = question_count;
    }
}
