package view.lcc.wyzsb.mvp.presenter;

import view.lcc.wyzsb.mvp.param.VideoParams;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2015年11月21日15:28:25
 * Description:
 */
public interface VideoPresenter {

    void getData(VideoParams params);

    void loadMore(VideoParams params);

    void refresh(VideoParams params);
}
