package com.lcc.mvp.presenter;

public interface ResetPasswordPresenter {

    void resetPassword(String pwd,String new_pwd);

    void getVerifySMS(String phone, String pwd);

}
