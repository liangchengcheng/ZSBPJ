package view.lcc.tyzs.mvp.view;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年04月15日20:00:59
 * Description:  |获取人数
 */
public interface PersonNumGetView {

    void PersonNumGetLoading();

    void PersonNumGetSuccess(String msg);

    void PersonNumGetFail(String msg);

    void NetWorkErr(String msg);
}
