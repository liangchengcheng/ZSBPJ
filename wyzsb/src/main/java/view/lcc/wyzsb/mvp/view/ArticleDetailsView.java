package view.lcc.wyzsb.mvp.view;

import view.lcc.wyzsb.bean.Article;

public interface ArticleDetailsView {

    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    void getDataSuccess(Article msg);
}
