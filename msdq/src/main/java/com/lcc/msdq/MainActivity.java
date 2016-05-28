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
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcc.base.BaseActivity;
import com.lcc.frame.update.UpdateApkTask;
import com.lcc.msdq.compony.CompanyIndexFragment;
import com.lcc.msdq.index.IndexFragment;
import com.lcc.msdq.personinfo.PersonInfoIndexFragment;
import com.lcc.msdq.test.TestIndexFragment;
import com.lcc.view.LivingTabsLayout;
import com.lcc.view.bottombar.BottomBar;
import com.lcc.view.bottombar.BottomBarFragment;
import com.lcc.view.menu.GuillotineAnimation;

import java.util.Locale;

import de.greenrobot.event.EventBus;
import zsbpj.lccpj.view.toast.SuperCustomToast;

public class MainActivity extends BaseActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        SuperCustomToast toast = SuperCustomToast.getInstance(getApplicationContext());
        toast.setDefaultTextColor(Color.WHITE);
        toast.show("不好了。", R.layout.toast_item,R.id.content_toast,MainActivity.this);

        mBottomBar = BottomBar.attach(MainActivity.this, savedInstanceState);
        mBottomBar.setFragmentItems(getSupportFragmentManager(), R.id.fragmentContainer,
                new BottomBarFragment(IndexFragment.newInstance(), R.drawable.ic_home_black_24dp, "主页"),
                new BottomBarFragment(TestIndexFragment.newInstance(), R.drawable.ic_receipt_black_24dp, "面试资料"),
                new BottomBarFragment(CompanyIndexFragment.newInstance(), R.drawable.ic_search_black_24dp, "公司真题"),
                new BottomBarFragment(PersonInfoIndexFragment.newInstance(), R.drawable.ic_perm_identity_black_24dp, "问题咨询")
        );

        UpdateApkTask task = new UpdateApkTask(MainActivity.this, false);
        task.detectionVersionInfo();

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


}
