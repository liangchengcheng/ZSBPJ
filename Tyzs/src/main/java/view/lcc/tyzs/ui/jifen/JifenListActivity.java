package view.lcc.tyzs.ui.jifen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.TabViewPagerAdapter;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.ui.jifen.fragment.KeyongFragment;
import view.lcc.tyzs.ui.jifen.fragment.XitongFragment;
import view.lcc.tyzs.ui.order.WeifukuanFragment;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-04 22:03
 * Description:  |
 */
public class JifenListActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jifen_list_activity);
        viewPager = (ViewPager) findViewById(R.id.second_viewpagers);
        tabLayout = (TabLayout) findViewById(R.id.second_tabs);
        setupViewPager();

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupViewPager() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final int f = tab.getPosition();
                viewPager.setCurrentItem(f);
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
        adapter.addFrag(new XitongFragment(), "系统积分");
        adapter.addFrag(new KeyongFragment(), "可用积分");
        viewPager.setAdapter(adapter);
    }

}
