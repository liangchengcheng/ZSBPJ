package view.lcc.wyzsb.mvp.view;

import view.lcc.wyzsb.bean.Article;
import view.lcc.wyzsb.bean.Link;

public interface LinkDetailsView {

    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    void getDataSuccess(Link msg);
}
