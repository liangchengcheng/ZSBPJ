package view.lcc.wyzsb.mvp.view;

import view.lcc.wyzsb.bean.Article;
import view.lcc.wyzsb.bean.News;

public interface NewsDetailsView {

    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    void getDataSuccess(News msg);
}
