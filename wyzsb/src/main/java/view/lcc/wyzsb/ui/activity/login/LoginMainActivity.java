package view.lcc.wyzsb.ui.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.adapter.TabViewPagerAdapter;
import view.lcc.wyzsb.base.BaseActivity;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月26日08:14:43
 * Description:
 */
public class LoginMainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_fragment);
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
        adapter.addFrag(new FragmentLogin(), "登录");
        adapter.addFrag(new FragmentRegister(), "注册");
        viewPager.setAdapter(adapter);
    }
}
