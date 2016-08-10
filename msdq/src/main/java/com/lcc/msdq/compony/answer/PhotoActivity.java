package com.lcc.msdq.compony.answer;

import com.lcc.base.BaseActivity;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2016年08月10日22:12:39
 * Description:  PhotoActivity
 */
public class PhotoActivity extends BaseActivity {

    public static final String url = "url";
    private String URL;

    @Override
    protected void initView() {
        URL = getIntent().getStringExtra(url);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return 0;
    }
}
