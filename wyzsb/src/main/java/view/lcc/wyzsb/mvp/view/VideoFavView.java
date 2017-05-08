package view.lcc.wyzsb.mvp.view;

import java.util.List;

import view.lcc.wyzsb.bean.History;
import view.lcc.wyzsb.bean.Videofav;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2015年11月21日15:28:25
 * Description:  |收藏的视频
 */
public interface VideoFavView {

    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    void refreshOrLoadFail(String msg);

    void refreshView(List<Videofav> entities);

    void loadMoreView(List<Videofav> entities);
}
