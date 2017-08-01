package view.lcc.tyzs.bean;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:49
 * Description:  |
 */
public class OrderDetails implements Serializable {
    private String GID;
    private String Gnumber;
    private String Profit;
    private String Cost;
    private String GName;
    private String GDescription;
    private String GImageURL;
    private String GIsDelete;

    public void setGID(String GID) {
        this.GID = GID;
    }

    public void setGIsDelete(String GIsDelete) {
        this.GIsDelete = GIsDelete;
    }

    public void setGImageURL(String GImageURL) {
        this.GImageURL = GImageURL;
    }

    public void setGName(String GName) {
        this.GName = GName;
    }

    public void setGnumber(String gnumber) {
        Gnumber = gnumber;
    }

    public void setProfit(String profit) {
        Profit = profit;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public void setGDescription(String GDescription) {
        this.GDescription = GDescription;
    }

    public String getGID() {
        return GID;
    }

    public String getGDescription() {
        return GDescription;
    }

    public String getCost() {
        return Cost;
    }

    public String getProfit() {
        return Profit;
    }

    public String getGnumber() {
        return Gnumber;
    }

    public String getGName() {
        return GName;
    }

    public String getGIsDelete() {
        return GIsDelete;
    }

    public String getGImageURL() {
        return GImageURL;
    }
}
