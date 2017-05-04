package view.lcc.wyzsb.ui.fragment;

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
import view.lcc.wyzsb.R;
import view.lcc.wyzsb.frame.ImageManager;
import view.lcc.wyzsb.ui.activity.login.LoginMainActivity;
import view.lcc.wyzsb.ui.activity.setting.AboutActivity;
import view.lcc.wyzsb.ui.activity.setting.BookActivity;
import view.lcc.wyzsb.ui.activity.setting.CollectionActivity;
import view.lcc.wyzsb.ui.activity.setting.FeedBackActivity;
import view.lcc.wyzsb.ui.activity.setting.HistoryActivity;
import view.lcc.wyzsb.ui.activity.setting.LinkActivity;
import view.lcc.wyzsb.ui.activity.setting.SystemActivity;
import view.lcc.wyzsb.utils.UserSharePreferenceUtil;
import view.lcc.wyzsb.view.pulltozoomview.PullToZoomScrollViewEx;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月09日12:04:14
 * Description:  设置界面
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
        content_view.findViewById(R.id.layout_me_task).setOnClickListener(this);
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
            //意见反馈
            case R.id.layout_me_task:
                FeedBackActivity.startFeedBackActivity(getActivity(), "");
                break;
            //关于软件
            case R.id.layout_me_getseed:
                AboutActivity.startAboutActivity(getActivity());
                break;
            //去登录
            case R.id.profile_headimg:
                if (TextUtils.isEmpty(UserSharePreferenceUtil.getUserSession())) {
                    LoginMainActivity.startLoginMainActivity("flag", getActivity());
                }
                break;
            //系统设置
            case R.id.layout_me_setting:
                intent = new Intent(getActivity(), SystemActivity.class);
                startActivity(intent);
                break;
            //博客导航
            case R.id.layout_me_host_manage:
                intent = new Intent(getActivity(), LinkActivity.class);
                startActivity(intent);
                break;
            //书籍推荐
            case R.id.layout_me_focus:
                intent = new Intent(getActivity(), BookActivity.class);
                startActivity(intent);
                break;
            //视频的收藏
            case R.id.layout_me_room_manage:
                intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
                break;
            //历史数据
            case R.id.layout_me_history:
                intent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void setData() {
        String phone = UserSharePreferenceUtil.getUserPhone();
        if (TextUtils.isEmpty(phone)) {
            return;
        }

        String nickname = UserSharePreferenceUtil.getUserName();
        if (TextUtils.isEmpty(nickname)) {
            nickname = UserSharePreferenceUtil.getUserPhone();
        }
        tv_phonenumber.setText(nickname);
        String url = UserSharePreferenceUtil.getUserImage();
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
