package com.lcc.msdq;

import android.view.View;
import android.widget.ImageView;

import com.lcc.base.BaseActivity;
import zsbpj.lccpj.frame.ImageManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  AboutActivity(关于作者)
 */
public class AboutActivity extends BaseActivity implements View.OnClickListener {

    private ImageView about_avatar_iv;

    @Override
    protected void initView() {
        findViewById(R.id.guillotine_hamburger).setOnClickListener(this);
        about_avatar_iv = (ImageView) findViewById(R.id.about_avatar_iv);
        ImageManager.getInstance().loadCircleResImage(AboutActivity.this, R.drawable.lcctx,
                about_avatar_iv);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_about;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guillotine_hamburger:
                finish();
                break;
        }
    }
}
