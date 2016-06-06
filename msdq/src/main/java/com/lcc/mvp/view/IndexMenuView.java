package com.lcc.mvp.view;

import com.lcc.entity.ActivityEntity;
import com.lcc.entity.Article;
import com.lcc.entity.WeekData;

import java.util.List;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  开始或者结束轮训器
 */
public interface IndexMenuView {

    /**
     * 第一次加载
     */
    void getLoading();

    /**
     * 获取空数据
     */
    void getDataEmpty();

    /**
     * 获取数据失败
     */
    void getDataFail(String msg);

    void refreshOrLoadFail(String msg);

    void refreshDataSuccess(List<Article> list);

    void loadMoreWeekDataSuccess(List<Article> entities);

}
