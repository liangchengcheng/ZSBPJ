package com.lcc.mvp.view;

public interface SignUpView {

    void showVerifyError(String errorMsg);

    void showVerifySuccess();

    void showSignUpError(String msg);

    void signUpSuccess();

    void showMsg(String msg);
}
