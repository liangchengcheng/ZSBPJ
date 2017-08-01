package view.lcc.tyzs.bean;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:54
 * Description:  |
 */
public class RouteInfo implements Serializable {
    private String info;
    private String time;

    public RouteInfo() {

    }

    public RouteInfo(String info, String time) {
        super();
        this.info = info;
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
