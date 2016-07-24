package com.lcc.mvp.view;

import com.lcc.entity.FavEntity;
import com.lcc.entity.otherUserInfo;

import java.util.List;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  GetUserInfoView(获取用户的简单的个人资料)
 */
public interface GetUserInfoView {

    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    void getDataSuccess(otherUserInfo otherUserInfo);

}