package view.lcc.tyzs.bean.request;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-06 19:12
 * Description:  |
 */
public class JifenZhuanzhangRequest implements Serializable {
    private String phone;
    private String aphone;
    private String value;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAphone() {
        return aphone;
    }

    public void setAphone(String aphone) {
        this.aphone = aphone;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
