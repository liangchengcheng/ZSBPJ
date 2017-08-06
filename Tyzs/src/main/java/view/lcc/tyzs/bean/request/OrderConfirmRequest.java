package view.lcc.tyzs.bean.request;

import java.io.Serializable;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-06 19:14
 * Description:  |
 */
public class OrderConfirmRequest implements Serializable {
    private String verification;
    private String content;

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
