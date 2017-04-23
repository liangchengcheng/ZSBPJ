package com.lcc.mvp.view;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  ChoiceTypeView
 */
public interface ChoiceTypeView {

    void getLoading1();

    void getLoading2();

    void getDataEmpty1();

    void getDataEmpty2();

    void getDataFail1(String msg);

    void getDataSuccess1(String msg);

    void getDataFail2(String msg);

    void getDataSuccess2(String msg);

    void setLoading();

    void setDataFail(String msg);

    void setDataSuccess();

}
