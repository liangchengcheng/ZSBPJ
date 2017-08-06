package view.lcc.tyzs.bean.request;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-06 19:06
 * Description:  |
 */
public class JifenTixianRequest implements Serializable {
    private String phone;
    private String value;
    private String name;
    private String CONTACT;
    private String BCID;
    private String bank;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }

    public String getBCID() {
        return BCID;
    }

    public void setBCID(String BCID) {
        this.BCID = BCID;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
