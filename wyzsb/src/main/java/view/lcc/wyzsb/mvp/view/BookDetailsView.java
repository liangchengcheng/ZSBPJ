package view.lcc.wyzsb.mvp.view;

import view.lcc.wyzsb.bean.Article;
import view.lcc.wyzsb.bean.Book;

public interface BookDetailsView {

    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    void getDataSuccess(Book msg);
}
