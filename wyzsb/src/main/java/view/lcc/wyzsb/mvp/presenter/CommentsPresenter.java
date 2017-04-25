package view.lcc.wyzsb.mvp.presenter;

import view.lcc.wyzsb.mvp.param.Replay;
import view.lcc.wyzsb.mvp.param.SendComments;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public interface CommentsPresenter {

    void getData(int page, String nid);

    void loadMore(int page, String nid);

    void refresh(int page, String nid);

    void sendComments(SendComments replay);

}
