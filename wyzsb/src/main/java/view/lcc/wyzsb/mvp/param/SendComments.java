package view.lcc.wyzsb.mvp.param;

import java.io.Serializable;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月25日20:55:10
 * Description:  要发送的评论
 */
public class SendComments implements Serializable{
    private String content;

    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
