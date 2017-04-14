package view.lcc.wyzsb.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.adapter.TabViewPagerAdapter;
import view.lcc.wyzsb.base.BaseFragment;
import view.lcc.wyzsb.bean.model.FilterData;
import view.lcc.wyzsb.bean.model.FilterEntity;
import view.lcc.wyzsb.bean.model.FilterTwoEntity;
import view.lcc.wyzsb.mvp.param.HomeParams;
import view.lcc.wyzsb.utils.DensityUtil;
import view.lcc.wyzsb.utils.ModelUtil;
import view.lcc.wyzsb.view.home.FilterView;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月08日15:38:09
 * Description:  主页
 */
public class DataFragment extends BaseFragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView point_city;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragment, null);
        viewPager = (ViewPager) view.findViewById(R.id.second_viewpagers);
        tabLayout = (TabLayout) view.findViewById(R.id.second_tabs);
        point_city = (TextView) view.findViewById(R.id.pointcity);
        setupViewPager();
        return view;
    }
    /**
     * 设置tab下的viewpager
     */
    private void setupViewPager() {
        point_city.setText("");
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
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new VideoFragment(), "视频教程");
        adapter.addFrag(new ArticleFragment(), "文字教程");
        adapter.addFrag(new NewsFragment(), "安全新闻");
        viewPager.setAdapter(adapter);
    }

    @Override
    public int initContentView() {
        return R.layout.data_fragment;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {

    }

    @Override
    public void initData() {

    }
}
