package com.hsfcompany.tzcs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsfcompany.tzcs.base.BaseFragment;
import com.hsfcompany.tzcs.ui.ResultActivity;
import com.hsfcompany.tzcs.ui.TanShiActivity;
import com.hsfcompany.tzcs.ui.TanshiTestMainActivity;
import com.hsfcompany.tzcs.ui.TestMainActivity;
import com.hsfcompany.tzcs.view.ColorArcProgressBar;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-13 11:47
 * Description:  |首页的布局
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private ColorArcProgressBar bar2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
        bar2 = (ColorArcProgressBar) view.findViewById(R.id.bar2);
        bar2.setCurrentValues(18);
        view.findViewById(R.id.mszb).setOnClickListener(this);
        view.findViewById(R.id.msjq).setOnClickListener(this);
        view.findViewById(R.id.msjl).setOnClickListener(this);
        view.findViewById(R.id.mszz).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            //体质测试
            case R.id.mszb:
                intent = new Intent(getActivity(), TestMainActivity.class);
                startActivity(intent);
                break;
            //痰湿与体重控制检测
            case R.id.msjl:
                intent = new Intent(getActivity(), TanshiTestMainActivity.class);
                startActivity(intent);
                break;
            //测试记录
            case R.id.msjq:
                intent = new Intent(getActivity(), ResultActivity.class);
                startActivity(intent);
                break;
            //健康知识
            case R.id.mszz:

                break;
        }
    }
}
