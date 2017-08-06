package view.lcc.tyzs.bean.request;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-06 18:04
 * Description:  |
 */
public class ChangePwdRequest implements Serializable {
    private String newpwd;
    private String phone;

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
