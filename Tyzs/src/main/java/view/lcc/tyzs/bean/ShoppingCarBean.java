package view.lcc.tyzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:57
 * Description:  |
 */
@Entity(nameInDb = "SHOP_CAR", createInDb = false)
public class ShoppingCarBean implements Serializable {
    static final long serialVersionUID = 1L;

    //商品id
    @Id
    @Property(nameInDb = "GID")
    private String GID;

    //零售价
    private String price;

    //成本
    private String cost;

    //利润
    private String profit;

    //商品名
    private String name;

    //商品描述
    private String description;

    //商品图片地址
    private String ImageUrl;

    //商品是否下架
    private String IsDelete;

    //商品数量
    private String number;

    @Transient
    private boolean isCheck;

    @Generated(hash = 524153276)
    public ShoppingCarBean(String GID, String price, String cost, String profit,
            String name, String description, String ImageUrl, String IsDelete,
            String number) {
        this.GID = GID;
        this.price = price;
        this.cost = cost;
        this.profit = profit;
        this.name = name;
        this.description = description;
        this.ImageUrl = ImageUrl;
        this.IsDelete = IsDelete;
        this.number = number;
    }

    @Generated(hash = 281679540)
    public ShoppingCarBean() {
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getGID() {
        return this.GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCost() {
        return this.cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getProfit() {
        return this.profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return this.ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public String getIsDelete() {
        return this.IsDelete;
    }

    public void setIsDelete(String IsDelete) {
        this.IsDelete = IsDelete;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
