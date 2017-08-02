package view.lcc.tyzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:55
 * Description:  |
 */
@Entity(nameInDb = "SHOP", createInDb = false)
public class ShoppingBean implements Serializable {
    static final long serialVersionUID = 2L;
    //描述
    private String GoodDescription;

    //ID
    @Id
    @Property(nameInDb = "GoodID")
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

    @Generated(hash = 1918161046)
    public ShoppingBean(String GoodDescription, String GoodID, String GoodName,
            String GoodPrice, String GoodCost, String GoodProfit, String GoodImgUrl,
            String count) {
        this.GoodDescription = GoodDescription;
        this.GoodID = GoodID;
        this.GoodName = GoodName;
        this.GoodPrice = GoodPrice;
        this.GoodCost = GoodCost;
        this.GoodProfit = GoodProfit;
        this.GoodImgUrl = GoodImgUrl;
        this.count = count;
    }

    @Generated(hash = 1245823369)
    public ShoppingBean() {
    }

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
