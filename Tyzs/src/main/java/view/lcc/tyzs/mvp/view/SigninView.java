package view.lcc.tyzs.mvp.view;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |10-29 10:48
 * Description:  |
 */
public interface SigninView {

    void onSigninLoading();

    void onSigninSuccess(String mes);

    void onSigninFail(String msg);

    void getSigninLoading();

    void getSigninSuccess(String rusult);

    void getSigninFail(String msg);
}
