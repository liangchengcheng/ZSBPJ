package view.lcc.tyzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:52
 * Description:  |
 */
@Entity(nameInDb = "NOTE", createInDb = false)
public class Note implements Serializable{


    @Id
    @Property(nameInDb = "ID")
    private String ID;

    private String Type;

    private String State;

    private String ChangeValue;

    private String Time;

    private String Spno;

    private String Balance;
}
