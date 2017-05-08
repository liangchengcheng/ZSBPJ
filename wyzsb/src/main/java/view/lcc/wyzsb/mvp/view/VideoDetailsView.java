package view.lcc.wyzsb.mvp.view;

import view.lcc.wyzsb.bean.Videofav;

public interface VideoDetailsView  {


    void getDataFail(String msg);

    void getDataSuccess(Videofav msg);

    /**
     * 收藏记录+1
     */
    void LookHistory();

    /**
     * 收藏记录+1失败
     */
    void LookHistoryFail(String msg);

    /**
     * 收藏成功
     */
    void FavSuccess();

    /**
     * 收藏失败
     */
    void FavFail(String msg);

    /**
     * 取消收藏成功
     */
    void UnFavSuccess();

    /**
     * 取消收藏失败
     */
    void UnFavFail(String msg);


}
