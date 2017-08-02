package view.lcc.tyzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:52
 * Description:  |
 */
@Entity(nameInDb = "NOTE", createInDb = false)
public class Note implements Serializable{
    static final long serialVersionUID = 3L;

    @Id
    @Property(nameInDb = "ID")
    private String ID;

    private String Type;

    private String State;

    private String ChangeValue;

    private String Time;

    private String Spno;

    private String Balance;

    @Generated(hash = 1109814466)
    public Note(String ID, String Type, String State, String ChangeValue,
            String Time, String Spno, String Balance) {
        this.ID = ID;
        this.Type = Type;
        this.State = State;
        this.ChangeValue = ChangeValue;
        this.Time = Time;
        this.Spno = Spno;
        this.Balance = Balance;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getType() {
        return this.Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getState() {
        return this.State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getChangeValue() {
        return this.ChangeValue;
    }

    public void setChangeValue(String ChangeValue) {
        this.ChangeValue = ChangeValue;
    }

    public String getTime() {
        return this.Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getSpno() {
        return this.Spno;
    }

    public void setSpno(String Spno) {
        this.Spno = Spno;
    }

    public String getBalance() {
        return this.Balance;
    }

    public void setBalance(String Balance) {
        this.Balance = Balance;
    }
}
