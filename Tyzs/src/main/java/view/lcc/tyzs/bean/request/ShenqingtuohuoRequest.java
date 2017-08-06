package view.lcc.tyzs.bean.request;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-06 19:20
 * Description:  |
 */
public class ShenqingtuohuoRequest implements Serializable {
    private String user;
    private String OID;
    private String Reason;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }
}
