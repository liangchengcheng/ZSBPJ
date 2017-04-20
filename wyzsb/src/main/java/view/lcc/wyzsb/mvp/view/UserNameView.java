package view.lcc.wyzsb.mvp.view;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:00:59
 * Description:
 */
public interface UserNameView {

    void UserNameLoading();

    void UserNameSuccess(String msg);

    void UserNameFail(String msg);

    void NetWorkErr(String msg);
}
