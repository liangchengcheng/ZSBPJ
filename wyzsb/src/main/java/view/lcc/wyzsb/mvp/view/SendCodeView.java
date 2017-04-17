package view.lcc.wyzsb.mvp.view;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:12:22
 * Description:
 */
public interface SendCodeView {

    void onSendLoading();

    void onSendNetworkError(String msg);

    void onRequestVcodeSuccess();

    void onRequestVcodeFail(String msg);
}
