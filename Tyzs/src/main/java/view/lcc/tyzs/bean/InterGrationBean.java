package view.lcc.tyzs.bean;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:51
 * Description:  |
 */
public class InterGrationBean implements Serializable {
    private String PID;
    private String SUID;
    private String sphone;
    private String sname;
    private String BUID;
    private String bphone;
    private String bname;
    private String OID;
    private String time;
    private String reason;
    private double value;

    public void setPID(String PID) {
        this.PID = PID;
    }

    public void setSUID(String SUID) {
        this.SUID = SUID;
    }

    public void setBUID(String BUID) {
        this.BUID = BUID;
    }

    public String getSUID() {
        return SUID;
    }

    public String getPID() {
        return PID;
    }

    public String getBUID() {
        return BUID;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public void setBphone(String bphone) {
        this.bphone = bphone;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getSname() {
        return sname;
    }

    public String getTime() {
        return time;
    }

    public String getReason() {
        return reason;
    }

    public double getValue() {
        return value;
    }

    public String getOID() {
        return OID;
    }

    public String getBphone() {
        return bphone;
    }

    public String getBname() {
        return bname;
    }

    public String getSphone() {
        return sphone;
    }
}
