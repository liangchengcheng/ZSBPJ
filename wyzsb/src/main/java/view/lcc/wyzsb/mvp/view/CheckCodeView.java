package view.lcc.wyzsb.mvp.view;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public interface CheckCodeView {

    /**
     * 发起网络等待
     */
    void onCheckLoading();

    void onCheckNetworkError(String msg);

    /**
     * 成功返回token
     */
    void onVcodeCheckSuccess(String token);

    void onVcodeCheckFail(String msg);
}
