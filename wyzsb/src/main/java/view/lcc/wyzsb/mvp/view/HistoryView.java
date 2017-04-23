package view.lcc.wyzsb.mvp.view;

import java.util.List;

import view.lcc.wyzsb.bean.Collection;
import view.lcc.wyzsb.bean.History;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public interface HistoryView {

    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    void refreshOrLoadFail(String msg);

    void refreshView(List<History> entities);

    void loadMoreView(List<History> entities);
}
