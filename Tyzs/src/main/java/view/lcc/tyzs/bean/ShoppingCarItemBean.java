package view.lcc.tyzs.bean;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:58
 * Description:  |
 */
public class ShoppingCarItemBean implements Serializable {

    //商品id
    private String GID;

    //商品数量
    private String number;

    public void setGID(String GID) {
        this.GID = GID;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public String getGID() {
        return GID;
    }


    public String getNumber() {
        return number;
    }
}
