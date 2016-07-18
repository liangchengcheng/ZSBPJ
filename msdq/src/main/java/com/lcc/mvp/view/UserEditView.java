package com.lcc.mvp.view;

public interface UserEditView {

    /**
     * 提交信息等待
     */
    void showLoading();

    /**
     * 编辑失败
     */
    void UserEditFail(String msg);

    /**
     * 编辑成功
     */
    void UserEditSuccess();
}
