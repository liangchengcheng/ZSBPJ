package view.lcc.wyzsb.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.view.pulltozoomview.PullToZoomScrollViewEx;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月09日12:04:14
 * Description:  设置界面
 */
public class SettingFragment extends Fragment {

    private PullToZoomScrollViewEx scrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_profilecenter, null);
        scrollView = (PullToZoomScrollViewEx) view.findViewById(R.id.scrollView);
        View header_view = View.inflate(getActivity(), R.layout.widget_profile_headview, null);

        View zoom_view = View.inflate(getActivity(), R.layout.widget_zoomview, null);
        View content_view = View.inflate(getActivity(), R.layout.widget_profile_contentview, null);

        scrollView.setHeaderView(header_view);
        scrollView.setZoomView(zoom_view);
        scrollView.setScrollContentView(content_view);
        return view;
    }


}
