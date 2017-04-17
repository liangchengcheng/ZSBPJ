package view.lcc.wyzsb.mvp.presenter;


import view.lcc.wyzsb.mvp.param.SendVcode;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:   SignInPresenter(验证手机号是否注册)
 */
public interface SendCodePresenter {
    void requestVCode(SendVcode phone);
}
