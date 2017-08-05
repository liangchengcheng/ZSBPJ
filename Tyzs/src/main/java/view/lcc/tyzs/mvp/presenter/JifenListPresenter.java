package view.lcc.tyzs.mvp.presenter;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-02 06:30
 * Description:  |
 */
public interface JifenListPresenter {
    void jifenList(String page,String type);

    void refresh(String page,String type);

    void loadMore(String page,String type);
}
