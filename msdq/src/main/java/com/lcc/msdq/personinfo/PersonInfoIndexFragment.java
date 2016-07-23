package com.lcc.msdq.personinfo;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.lcc.db.test.UserInfo;
import com.lcc.frame.data.DataManager;
import com.lcc.msdq.description.user.UserProfileActivity;
import com.lcc.msdq.favorite.FavoriteList;
import com.lcc.msdq.login.LoginActivity;
import com.lcc.msdq.R;
import com.lcc.msdq.login.ResetPasswordActivity;
import com.lcc.msdq.fabu.FabuList;
import com.lcc.msdq.setting.SettingActivity;

import zsbpj.lccpj.frame.ImageManager;
import zsbpj.lccpj.view.toast.SuperCustomToast;

public class PersonInfoIndexFragment extends Fragment implements View.OnClickListener {

    //头像
    private ImageView iv_more;
    //用户名
    private TextView tv_username;
    //签名
    private TextView tv_qm;
    //签到
    private RelativeLayout rl_qd;
    //
    private TextView tv_qd;

    public static Fragment newInstance() {
        return new PersonInfoIndexFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_info_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx)
                view.findViewById(R.id.scroll_view);
        View headView = LayoutInflater.from(getActivity())
                .inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(getActivity())
                .inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(getActivity())
                .inflate(R.layout.profile_content_view, null, false);

        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);

        view.findViewById(R.id.ll_change_pwd).setOnClickListener(this);
        view.findViewById(R.id.ll_fabu).setOnClickListener(this);
        view.findViewById(R.id.iv_more).setOnClickListener(this);
        view.findViewById(R.id.ll_fav).setOnClickListener(this);
        view.findViewById(R.id.iv_sys_image).setOnClickListener(this);
        view.findViewById(R.id.rl_help).setOnClickListener(this);
        iv_more = (ImageView) view.findViewById(R.id.iv_more);
        tv_username = (TextView) view.findViewById(R.id.tv_username);
        tv_username.setOnClickListener(this);
        tv_qm = (TextView) view.findViewById(R.id.tv_qm);
        rl_qd = (RelativeLayout) view.findViewById(R.id.rl_qd);
        tv_qd = (TextView) view.findViewById(R.id.tv_qd);
        rl_qd.setOnClickListener(this);
        view.findViewById(R.id.tv_username).setOnClickListener(this);
        setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_username:
            case R.id.iv_more:
                if (DataManager.getUserInfo() != null) {
                    Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                    intent.putExtra(UserProfileActivity.UserInfo, DataManager.getUserInfo());
                    startActivityForResult(intent, 200);
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 100);
                }
                break;

            case R.id.rl_qd:
                SuperCustomToast toast = SuperCustomToast.getInstance(getActivity());
                toast.setDefaultTextColor(Color.WHITE);
                toast.show("签到成功。", R.layout.layout_qd, R.id.content_toast,
                        getActivity());
                break;

            case R.id.iv_sys_image:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;

            case R.id.ll_fav:
                startActivity(new Intent(getActivity(), FavoriteList.class));
                break;

            case R.id.ll_fabu:
                startActivity(new Intent(getActivity(), FabuList.class));
                break;

            case R.id.ll_change_pwd:
                startActivity(new Intent(getActivity(), ResetPasswordActivity.class));
                break;

            case R.id.rl_help:
                startActivity(new Intent(getActivity(), HelpActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == 100) {
            setData();
        }
    }

    /**
     * 设置自己的数据。
     */
    private void setData() {
        UserInfo userInfo = DataManager.getUserInfo();
        if (userInfo != null) {
            tv_username.setText(userInfo.getNickname());

            if (TextUtils.isEmpty(userInfo.getQm())) {
                tv_qm.setText("这个家伙很懒，什么也没留下");
            } else {
                tv_qm.setText(userInfo.getQm());
            }

            String user_image = userInfo.getUser_image();
            if (!TextUtils.isEmpty(user_image)) {
                ImageManager.getInstance().loadCircleImage(getActivity(), user_image,
                        iv_more);
            }
        }
    }

}
