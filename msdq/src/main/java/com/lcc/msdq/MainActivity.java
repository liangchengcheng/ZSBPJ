package com.lcc.msdq;

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

import com.lcc.msdq.compony.CompanyIndexFragment;
import com.lcc.msdq.index.IndexFragment;
import com.lcc.msdq.personinfo.PersonInfoIndexFragment;
import com.lcc.msdq.test.TestIndexFragment;
import com.lcc.view.LivingTabsLayout;
import com.lcc.view.bottombar.BottomBar;
import com.lcc.view.bottombar.BottomBarFragment;
import com.lcc.view.menu.GuillotineAnimation;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBottomBar = BottomBar.attach(MainActivity.this, savedInstanceState);
        mBottomBar.setFragmentItems(getSupportFragmentManager(), R.id.fragmentContainer,
                new BottomBarFragment(IndexFragment.newInstance(), R.drawable.ic_home_black_24dp, "主页"),
                new BottomBarFragment(TestIndexFragment.newInstance(), R.drawable.ic_receipt_black_24dp, "面试题"),
                new BottomBarFragment(CompanyIndexFragment.newInstance(), R.drawable.ic_search_black_24dp, "公司题"),
                new BottomBarFragment(PersonInfoIndexFragment.newInstance(), R.drawable.ic_perm_identity_black_24dp, "问题咨询")
        );
    }

}
