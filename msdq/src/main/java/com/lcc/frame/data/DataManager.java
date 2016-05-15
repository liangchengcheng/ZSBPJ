package com.lcc.frame.data;

import android.content.Context;
import com.lcc.App;
import com.lcc.db.test.UserInfo;
import com.lcc.db.test.UserInfoDao;
import java.util.List;
import de.greenrobot.dao.query.Query;
import zsbpj.lccpj.frame.FrameManager;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:  DataManager 管理保存的用户信息
 */
public class DataManager {

    /**
     * 根据主键删除实体
     */
    public static void deleteAllUser() {
        getNoteDao(FrameManager.getAppContext()).deleteAll();
    }


    public static UserInfoDao getNoteDao(Context context) {
        return ((App) context.getApplicationContext()).getDaoSession().getUserInfoDao();
    }

    /**
     * 向数据库里插入登录信息
     */
    public static void saveUserInfo(UserInfo userInfo) {
        getNoteDao(FrameManager.getAppContext()).deleteAll();
        getNoteDao(FrameManager.getAppContext()).insert(userInfo);

    }

    /**
     * 获取所有用户的信息
     */
    public static UserInfo getUserInfo() {
        Query query = getNoteDao(FrameManager.getAppContext()).queryBuilder()
                .build();
        List<UserInfo>userInfos= query.list();
        if (userInfos!=null&&userInfos.size()>0){
            return  userInfos.get(0);
        }
        return null;
    }


}
