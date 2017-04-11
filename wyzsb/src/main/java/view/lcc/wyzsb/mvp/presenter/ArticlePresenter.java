package view.lcc.wyzsb.mvp.presenter;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public interface ArticlePresenter {
    void getData(int page,String type);

    void loadMore(int page,String type);

    void refresh(int page,String type);

}
