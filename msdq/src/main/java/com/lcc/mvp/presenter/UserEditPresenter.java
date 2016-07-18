package com.lcc.mvp.presenter;

import com.lcc.entity.UserInfo;

import java.io.File;
import java.util.List;

public interface UserEditPresenter {
    void userEdit(UserInfo userInfo,List<File> files);
}
