package com.lcc.mvp.view;

import com.lcc.entity.CompanyTest;

import java.util.List;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  开始或者结束轮训器
 */
public interface ChoiceTypeView {

    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    void getDataSuccess(String msg);

    void setLoading();

    void setDataFail(String msg);

    void setDataSuccess();

}
