package view.lcc.tyzs.ui.home;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.MyPagerAdapter;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.ui.car.CarFragment;
import view.lcc.tyzs.ui.goods.GoodsFragment;
import view.lcc.tyzs.ui.setting.SettingFragment;
import view.lcc.tyzs.utils.SystemBarHelper;
import view.lcc.tyzs.view.NoScrollViewPager;
import view.lcc.tyzs.view.navigationbar.NavigationTabBar;

public class MainActivity  extends BaseActivity {

    private ArrayList<Fragment> fgList;

    private NoScrollViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SystemBarHelper.immersiveStatusBar(this);
        //SystemBarHelper.immersiveStatusBar(this, 0);
        //SystemBarHelper.setHeightAndPadding(this, mToolbar);

        initFragment();
        initUI();
    }

    private void initFragment() {
        fgList = new ArrayList<Fragment>();
        HomeFragment homeFragment = new HomeFragment();
        GoodsFragment goodsFragment = new GoodsFragment();
        CarFragment carFragment = new CarFragment();
        SettingFragment settingFragment = new SettingFragment();
        fgList.add(homeFragment);
        fgList.add(goodsFragment);
        fgList.add(carFragment);
        fgList.add(settingFragment);
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
                .title("").build()
        );
        models.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.sort_press),
                Color.parseColor(colors[0]))
                .title("").build()
        );
        models.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.car_press),
                Color.parseColor(colors[0]))
                .title("").build()
        );

        models.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.my_press),
                Color.parseColor(colors[0]))
                .title("").build()
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
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


    public void setCurrent(int position){
        if ( viewPager!= null){
            viewPager.setCurrentItem(position);
        }
    }

}
