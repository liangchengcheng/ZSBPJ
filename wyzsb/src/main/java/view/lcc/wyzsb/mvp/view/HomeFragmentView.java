package view.lcc.wyzsb.mvp.view;

import java.util.List;

import view.lcc.wyzsb.bean.model.TravelingEntity;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public interface HomeFragmentView {

    /**
     * 获取首页文章列表
     */
    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    void getDataSuccess(List<TravelingEntity> entities);

    void loadMoreDataSuccess(List<TravelingEntity> entities);

    void loadMoreDataFail(String msg);
}
