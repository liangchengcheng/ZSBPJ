package view.lcc.tyzs.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:48
 * Description:  |
 */
public class OrderBean implements Serializable{
    private String  user;
    private String  AID;
    private String  msg;
    private String  orderTotal;
    private String  point;
    private String  creatTime;
    private ArrayList<OrderInfo> orderInfo;

    public ArrayList<OrderInfo> getOrderInfo() {
        return orderInfo;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public void setOrderInfo(ArrayList<OrderInfo> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getUser() {
        return user;
    }

    public String getMsg() {
        return msg;
    }

    public String getAID() {
        return AID;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public String getPoint() {
        return point;
    }

    public String getCreatTime() {
        return creatTime;
    }
}
