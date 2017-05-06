package view.lcc.wyzsb.mvp.param;

import java.io.Serializable;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class VideoParams implements Serializable {
    private int page;

    private String v_type;
    private String more;
    private String v_l;

    public String getV_type() {
        return v_type;
    }

    public void setV_type(String v_type) {
        this.v_type = v_type;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getV_l() {
        return v_l;
    }

    public void setV_l(String v_l) {
        this.v_l = v_l;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
