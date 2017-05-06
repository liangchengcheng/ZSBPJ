package view.lcc.wyzsb.mvp.presenter;

import view.lcc.wyzsb.bean.Article;
import view.lcc.wyzsb.mvp.param.ArticleParams;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public interface ArticlePresenter {

    void getData(ArticleParams article);

    void loadMore(ArticleParams article);

    void refresh(ArticleParams article);

}
