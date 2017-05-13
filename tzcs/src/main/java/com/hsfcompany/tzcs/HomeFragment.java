package com.hsfcompany.tzcs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsfcompany.tzcs.base.BaseFragment;
import com.hsfcompany.tzcs.view.ColorArcProgressBar;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-13 11:47
 * Description:  |首页的布局
 */
public class HomeFragment extends BaseFragment {
    private ColorArcProgressBar bar2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,null);
        bar2 = (ColorArcProgressBar) view.findViewById(R.id.bar2);
        bar2.setCurrentValues(18);
        return view;
    }
}
