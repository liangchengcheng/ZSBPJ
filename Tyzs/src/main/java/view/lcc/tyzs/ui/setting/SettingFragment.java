package view.lcc.tyzs.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import view.lcc.tyzs.R;
import view.lcc.tyzs.frame.ImageManager;
import view.lcc.tyzs.ui.address.AddressListActivity;
import view.lcc.tyzs.utils.SharePreferenceUtil;
import view.lcc.tyzs.view.pulltozoomview.PullToZoomScrollViewEx;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |07-31 20:52
 * Description:  |
 */
public class SettingFragment extends Fragment implements View.OnClickListener {
    private PullToZoomScrollViewEx scrollView;
    private TextView tv_phonenumber;
    private ImageView profile_headimg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_profilecenter, null);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        scrollView = (PullToZoomScrollViewEx) view.findViewById(R.id.scrollView);
        View header_view = View.inflate(getActivity(), R.layout.widget_profile_headview, null);

        tv_phonenumber = (TextView) header_view.findViewById(R.id.tv_phonenumber);
        profile_headimg = (ImageView) header_view.findViewById(R.id.profile_headimg);

        header_view.findViewById(R.id.profile_headimg).setOnClickListener(this);
        header_view.findViewById(R.id.layout_me_setting).setOnClickListener(this);


        View zoom_view = View.inflate(getActivity(), R.layout.widget_zoomview, null);


        View content_view = View.inflate(getActivity(), R.layout.widget_profile_contentview, null);

        content_view.findViewById(R.id.layout_me_getseed).setOnClickListener(this);
        content_view.findViewById(R.id.layout_me_host_manage).setOnClickListener(this);
        content_view.findViewById(R.id.layout_me_focus).setOnClickListener(this);
        content_view.findViewById(R.id.layout_me_room_manage).setOnClickListener(this);
        content_view.findViewById(R.id.layout_me_history).setOnClickListener(this);


        scrollView.setHeaderView(header_view);
        scrollView.setZoomView(zoom_view);
        scrollView.setScrollContentView(content_view);
        setData();
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            //关于软件
            case R.id.layout_me_getseed:

                break;
            //去登录
            case R.id.profile_headimg:

                break;
            //系统设置
            case R.id.layout_me_setting:


                break;
            //博客导航
            case R.id.layout_me_host_manage:

                break;
            //书籍推荐
            case R.id.layout_me_focus:

                break;
            //地址管理
            case R.id.layout_me_room_manage:
                // TODO: 2017/8/6 判断是否登录了
                intent = new Intent(getActivity(), AddressListActivity.class);
                startActivity(intent);
                break;
            //历史数据
            case R.id.layout_me_history:

                break;

        }
    }

    private void setData() {
        String phone = SharePreferenceUtil.getUid();
        if (TextUtils.isEmpty(phone)) {
            return;
        }

        String nickname = SharePreferenceUtil.getUid();
        if (TextUtils.isEmpty(nickname)) {
            nickname = SharePreferenceUtil.getUid();
        }
        tv_phonenumber.setText(nickname);
        String url = SharePreferenceUtil.getUid();
        //这里等后期添加了
        if (TextUtils.isEmpty(url)) {
            ImageManager.getInstance()
                    .loadResImage(getContext(), R.mipmap.ic_launcher, profile_headimg);
        }
    }

    public void onEvent(Integer event) {
        switch (event) {
            case 0x03:
                setData();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}

