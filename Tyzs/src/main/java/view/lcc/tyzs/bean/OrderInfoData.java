package view.lcc.tyzs.bean;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:53
 * Description:  |
 */
public class OrderInfoData implements Serializable {

    private String OID;
    private String creattime;
    private String gettime;
    private String paytime;
    private String AID;
    private String total;
    private String point;
    private String ispay;
    private String paytype;
    private String msg;

    public void setIsApply(String isApply) {
        this.isApply = isApply;
    }

    public String getIsApply() {
        return isApply;
    }

    private String isApply;

    public String getOID() {
        return OID;
    }

    public String getCreattime() {
        return creattime;
    }

    public String getGettime() {
        return gettime;
    }

    public String getPoint() {
        return point;
    }

    public String getMsg() {
        return msg;
    }

    public String getPaytype() {
        return paytype;
    }

    public String getIspay() {
        return ispay;
    }

    public String getTotal() {
        return total;
    }

    public String getAID() {
        return AID;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public void setGettime(String gettime) {
        this.gettime = gettime;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setIspay(String ispay) {
        this.ispay = ispay;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

}
