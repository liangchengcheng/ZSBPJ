package com.lcc.msdq;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lcc.base.BaseActivity;
import com.lcc.frame.update.UpdateApkTask;
import com.lcc.msdq.compony.CompanyIndexFragment;
import com.lcc.msdq.index.IndexFragment;
import com.lcc.msdq.personinfo.PersonInfoIndexFragment;
import com.lcc.msdq.test.TestIndexFragment;
import com.lcc.utils.SharePreferenceUtil;
import com.lcc.view.LivingTabsLayout;
import com.lcc.view.bottombar.BottomBar;
import com.lcc.view.bottombar.BottomBarFragment;
import com.lcc.view.menu.GuillotineAnimation;

import java.util.Locale;

import de.greenrobot.event.EventBus;
import zsbpj.lccpj.utils.TimeUtils;
import zsbpj.lccpj.view.toast.SuperCustomToast;

public class MainActivity extends BaseActivity {

    private BottomBar mBottomBar;
    private long firstTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        mBottomBar = BottomBar.attach(MainActivity.this, savedInstanceState);
        mBottomBar.setFragmentItems(getSupportFragmentManager(), R.id.fragmentContainer,
                new BottomBarFragment(IndexFragment.newInstance(), R.drawable.ic_home_black_24dp, "主页"),
                new BottomBarFragment(TestIndexFragment.newInstance(), R.drawable.ic_receipt_black_24dp, "面试资料"),
                new BottomBarFragment(CompanyIndexFragment.newInstance(), R.drawable.ic_search_black_24dp, "公司真题"),
                new BottomBarFragment(PersonInfoIndexFragment.newInstance(), R.drawable.ic_perm_identity_black_24dp, "个人中心")
        );

        String updateTime = SharePreferenceUtil.getUpdateTime();
        if (!TextUtils.isEmpty(updateTime)) {
            String localtime = TimeUtils.StrTime(System.currentTimeMillis());
            if ((Long.parseLong(localtime) - Long.parseLong(updateTime)) / (60 * 60 * 24) > 7) {
                updateAPK();
            }
        }

        if (updateTime == null) {
            updateAPK();
        }
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
        return R.layout.index;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

    public void onEvent(Integer event) {
        switch (event) {
            case 0x02:
                this.recreate();
                break;
        }
    }

    public void updateAPK() {
        UpdateApkTask task = new UpdateApkTask(MainActivity.this, false);
        task.detectionVersionInfo();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 800) {
                Toast.makeText(MainActivity.this, "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
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

}
