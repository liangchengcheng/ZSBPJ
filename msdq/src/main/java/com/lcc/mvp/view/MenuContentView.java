package com.lcc.mvp.view;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  MenuContentView
 */
public interface MenuContentView {

    void Loading();

    void getFail(String msg);

    void getSuccess(String result);

    void FavSuccess();

    void FavFail(String msg);

}
