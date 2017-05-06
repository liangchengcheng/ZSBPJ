package view.lcc.wyzsb.mvp.param;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2015年11月21日15:28:25
 * Description:
 */
public class ArticleParams implements Serializable{

    private String a_l;
    private String a_c;
    private String a_type;

    public String getA_l() {
        return a_l;
    }

    public void setA_l(String a_l) {
        this.a_l = a_l;
    }

    public String getA_c() {
        return a_c;
    }

    public void setA_c(String a_c) {
        this.a_c = a_c;
    }

    public String getA_type() {
        return a_type;
    }

    public void setA_type(String a_type) {
        this.a_type = a_type;
    }

    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
