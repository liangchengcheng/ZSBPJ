package view.lcc.tyzs.mvp.view;

import java.util.List;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:00:59
 * Description:
 */
public interface GetOrderView {

    void GetOrderLoading();

    void GetOrderSuccess(String msg);

    void GetOrderFail(String msg);

    void NetWorkErr(String msg);

    void refreshOrLoadFail(String msg);

    void refreshDataSuccess(String msg);

    void loadMoreWeekDataSuccess(String msg);
}
