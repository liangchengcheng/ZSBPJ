package view.lcc.tyzs.bean;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:55
 * Description:  |
 */
public class ShoppingBean implements Serializable {

    //描述
    private String GoodDescription;

    //ID
    private String GoodID;

    //名字
    private String GoodName;

    //价格
    private String GoodPrice;

    //成本
    private String GoodCost;

    //利润
    private String GoodProfit;

    //图片
    private String GoodImgUrl;

    //商品的数量
    private  String count;

    public String getGoodDescription() {
        return GoodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        GoodDescription = goodDescription;
    }

    public String getGoodID() {
        return GoodID;
    }

    public void setGoodID(String goodID) {
        GoodID = goodID;
    }

    public String getGoodName() {
        return GoodName;
    }

    public void setGoodName(String goodName) {
        GoodName = goodName;
    }

    public String getGoodPrice() {
        return GoodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        GoodPrice = goodPrice;
    }

    public String getGoodCost() {
        return GoodCost;
    }

    public void setGoodCost(String goodCost) {
        GoodCost = goodCost;
    }

    public String getGoodProfit() {
        return GoodProfit;
    }

    public void setGoodProfit(String goodProfit) {
        GoodProfit = goodProfit;
    }

    public String getGoodImgUrl() {
        return GoodImgUrl;
    }

    public void setGoodImgUrl(String goodImgUrl) {
        GoodImgUrl = goodImgUrl;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
