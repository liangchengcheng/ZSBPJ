package view.lcc.tyzs.bean;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:49
 * Description:  |
 */
public class OrderInfo implements Serializable {
    private String GID;
    private String number;
    private String trueprice;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTrueprice(String trueprice) {
        this.trueprice = trueprice;
    }

    public String getTrueprice() {

        return trueprice;
    }

    public OrderInfo(String GID, String number) {
        this.GID = GID;
        this.number = number;
    }
    public OrderInfo() {

    }

    public String getGID() {
        return GID;
    }

    public String getNumber() {
        return number;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
