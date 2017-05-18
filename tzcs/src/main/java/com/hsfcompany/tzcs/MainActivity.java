package com.hsfcompany.tzcs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hsfcompany.tzcs.adapter.MyPagerAdapter;
import com.hsfcompany.tzcs.ui.setting.AboutActivity;
import com.hsfcompany.tzcs.ui.setting.JieshaoActivity;
import com.hsfcompany.tzcs.view.NoScrollViewPager;
import com.hsfcompany.tzcs.view.navigationbar.NavigationTabBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ArrayList<Fragment> fgList;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private HomeFragment homeFragment;
    private DataFragment dataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragmentPager();
        initDrawer();
        initUI();
    }

    private void initFragmentPager() {
        fgList = new ArrayList<Fragment>();
        homeFragment = new HomeFragment();
        dataFragment = new DataFragment();
        fgList.add(homeFragment);
        fgList.add(dataFragment);
    }
    private NoScrollViewPager viewPager;

    public void setCurrent(){
        if (viewPager != null){
            viewPager.setCurrentItem(1);
        }
    }

    private void initUI() {
        viewPager = (NoScrollViewPager) findViewById(R.id.vp_horizontal_ntb);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fgList);
        viewPager.setAdapter(myPagerAdapter);

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        NavigationTabBar.OnTabBarSelectedIndexListener onTabBarSelectedIndexListener = navigationTabBar.getOnTabBarSelectedIndexListener();
        navigationTabBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("====", "被点击了" + v.getId());
            }
        });

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(NavigationTabBar.Model model, int index) {
                Log.i("====", "开始被选择" + index);
                if (index == 2) drawer.openDrawer(navigationView);
            }

            @Override
            public void onEndTabSelected(NavigationTabBar.Model model, int index) {
                Log.i("====", "结束被选择" + index);
            }
        });

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.homenormal),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.homeselect))
                        .title("主页")
                        .build()
        );
        models.add(new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.sort_press),
                        Color.parseColor(colors[1]))
                        .title("知识")
                        .build()
        );

        models.add(new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.my_press),
                        Color.parseColor(colors[4]))
                        //.selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("我的")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }

    /**
     * 初始化抽屉
     */
    private void initDrawer() {
        //https://segmentfault.com/a/1190000004151222
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
            View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
            RelativeLayout headerBackground = (RelativeLayout) headerLayout.findViewById(R.id.header_background);
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*	ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
					R.string.navigation_drawer_close);
			drawer.addDrawerListener(toggle);
			toggle.syncState();*/
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_app) {
            Intent intent = new Intent(MainActivity.this, JieshaoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_set) {
            Toast.makeText(MainActivity.this, "缓存已经清除", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }
}
