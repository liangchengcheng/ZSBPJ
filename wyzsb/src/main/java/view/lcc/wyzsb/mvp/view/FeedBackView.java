package view.lcc.wyzsb.mvp.view;

public interface FeedBackView {

    /**
     * 等待
     */
    void showLoading();

    /**
     * 提交：错误信息
     */
    void FeekFail(String msg);

    /**
     * 登录成功
     */
    void FeekSuccess();
}
