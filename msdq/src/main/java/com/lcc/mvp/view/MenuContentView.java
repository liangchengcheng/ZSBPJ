package com.lcc.mvp.view;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  开始或者结束轮训器
 */
public interface MenuContentView {

    void Loading();
    /**
     * 错误信息
     */
    void getFail(String msg);

    /**
     * 获取成功
     */
    void getSuccess(String result);

}
