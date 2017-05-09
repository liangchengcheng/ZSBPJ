package view.lcc.wyzsb.mvp.presenter;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public interface HistoryPresenter {

    void getData(int page);

    void loadMore(int page);

    void refresh(int page);
}
