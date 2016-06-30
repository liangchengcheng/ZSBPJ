package com.lcc.mvp.presenter;

public interface ResetPasswordPresenter {

    void resetPassword(String verify_code, String phone, String pwd,String new_pwd);

    void getVerifySMS(String phone, String pwd);

}
