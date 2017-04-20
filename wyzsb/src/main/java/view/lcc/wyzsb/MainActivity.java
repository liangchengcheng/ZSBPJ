package view.lcc.wyzsb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import net.youmi.android.normal.spot.SpotManager;

import java.util.ArrayList;

import view.lcc.wyzsb.adapter.MyPagerAdapter;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.frame.Frame;
import view.lcc.wyzsb.ui.fragment.DataFragment;
import view.lcc.wyzsb.ui.fragment.HomeFragment;
import view.lcc.wyzsb.ui.fragment.SettingFragment;
import view.lcc.wyzsb.view.NoScrollViewPager;
import view.lcc.wyzsb.view.navigationbar.NavigationTabBar;

public class MainActivity extends BaseActivity {

    private ArrayList<Fragment> fgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initUI();
    }

    private void initFragment() {
        fgList = new ArrayList<Fragment>();
        HomeFragment homeFragment = new HomeFragment();
        DataFragment dataFragment = new DataFragment();
        SettingFragment settingFragment = new SettingFragment();
        fgList.add(homeFragment);
        fgList.add(dataFragment);
        fgList.add(settingFragment);
    }

    private void initUI() {
        final NoScrollViewPager viewPager = (NoScrollViewPager) findViewById(R.id.vp_horizontal_ntb);
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
                if (index == 2) {}
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
                        .title("IYO").build()
        );
        models.add(new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.sort_press),
                        Color.parseColor(colors[1]))
                        .title("月子中心").build()
        );

        models.add(new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.my_press),
                        Color.parseColor(colors[4]))
                        .title("我的").build()
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

    private long firstTime;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 800) {
                Frame.getInstance().toastPrompt("再按一次退出程序");
                firstTime = secondTime;
                return true;
            } else {
                exitApp();
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    public void exitApp() {
        SpotManager.getInstance(MainActivity.this).onAppExit();
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
