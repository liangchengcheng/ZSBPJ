package view.lcc.wyzsb.mvp.presenter;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public interface BookPresenter {

    void getData(int page, String options);

    void loadMore(int page, String options);

    void refresh(int page, String options);
}
