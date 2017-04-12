package view.lcc.wyzsb.mvp.view;

import java.util.List;

import view.lcc.wyzsb.bean.News;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public interface NewsView {

    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    void refreshOrLoadFail(String msg);

    void refreshView(List<News> entities);

    void loadMoreView(List<News> entities);
}
