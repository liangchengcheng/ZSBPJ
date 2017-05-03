package com.lcc.msdq.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.WindowManager;
import com.lcc.adapter.TabViewPagerAdapter;
import com.lcc.msdq.R;
import com.lcc.msdq.comments.CommentsActivity;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月20日13:43:06
 * Description:  新的登录的界面
 */
public class LoginMainActivity extends FragmentActivity {
    private String result;

    public static void startLoginMainActivity(String r, Activity startActivity) {
        Intent intent = new Intent(startActivity, LoginMainActivity.class);
        intent.putExtra("result", r);
        startActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login_layout);
        result = getIntent().getStringExtra("result");
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
                final 	int f=tab.getPosition();
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
        if (TextUtils.isEmpty(result)){
            adapter.addFrag( FragmentLogin.newInstance(result), "登录");
            adapter.addFrag( FragmentRegister.newInstance(result), "注册");
        }else {
            adapter.addFrag( FragmentLogin.newInstance(""), "登录");
            adapter.addFrag( FragmentRegister.newInstance(""), "注册");
        }

        viewPager.setAdapter(adapter);
        if (!TextUtils.isEmpty(result) && result.equals("tv_register")){
            viewPager.setCurrentItem(1);
        }
    }

}
