package com.lcc.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lcc.activity.R;
import com.lcc.base.BaseActivity;
import com.lcc.bean.News;
import com.lcc.rx.RxService;
import com.lcc.service.ApiService;
import java.util.ArrayList;
import java.util.List;
import retrofit.Call;
import zsbpj.lccpj.app.activity.StartNetActivity;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:  登录界面的简单的实现
 */
public class LoginActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean Open() {
        return true;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_login;
    }
}
