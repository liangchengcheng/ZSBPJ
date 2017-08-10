package view.lcc.tyzs.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.WindowManager;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.TabViewPagerAdapter;
import view.lcc.tyzs.base.BaseActivity;


/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年04月26日08:14:43
 * Description:  |
 */
public class LoginMainActivity extends BaseActivity {

    private String result = "";

    public static void startLoginMainActivity(String r, Activity startActivity) {
        Intent intent = new Intent(startActivity, LoginMainActivity.class);
        intent.putExtra("result", r);
        startActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_fragment);
        result = getIntent().getStringExtra("result");
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setupViewPager();
    }

    /**
     * 设置tab下的viewpager
     */
    private void setupViewPager() {
        final ViewPager login_viewpager = (ViewPager) findViewById(R.id.login_viewpager);
        setupViewPager(login_viewpager);
        TabLayout login_tabs = (TabLayout) findViewById(R.id.login_tabs);
        login_tabs.setupWithViewPager(login_viewpager);
        login_tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final int f=tab.getPosition();
                login_viewpager.setCurrentItem(f);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag( FragmentLogin.newInstance(result), "账号登录");
        adapter.addFrag( FragmentRegister.newInstance(result), "会员注册");
        viewPager.setAdapter(adapter);
    }
}
