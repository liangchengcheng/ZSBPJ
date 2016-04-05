package com.lcc.msdq.index;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lcc.base.BaseFragment;
import com.lcc.frame.Advertisements;
import com.lcc.frame.update.UpdateApkTask;
import com.lcc.msdq.R;
import com.lcc.view.menu.GuillotineAnimation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IndexFragment extends BaseFragment {

    private LinearLayout llAdvertiseBoard;
    private LayoutInflater inflaters;
    private FrameLayout root;
    private static final long RIPPLE_DURATION = 250;
    private ImageView iv_menu;

    public static Fragment newInstance() {
        Fragment fragment = new IndexFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.index_fragment,null);
        Toolbar toolbar= (Toolbar) view.findViewById(R.id.toolbar);
        iv_menu= (ImageView) view.findViewById(R.id.iv_menu);
        root= (FrameLayout) view.findViewById(R.id.root);
        View guillotineMenu
                = LayoutInflater.from(getActivity()).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);
        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), iv_menu)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
        inflaters = LayoutInflater.from(getActivity());
        llAdvertiseBoard = (LinearLayout) view.findViewById(R.id.llAdvertiseBoard);
        initViews();
        UpdateApkTask task = new UpdateApkTask(getActivity(), false);
        task.detectionVersionInfo();

        return view;
    }

    private void initViews() {
        JSONArray advertiseArray = new JSONArray();
        try {
            JSONObject head_img0 = new JSONObject();
            head_img0.put("head_img", "http://www.17yxb.cn/image/m1.png");
            JSONObject head_img1 = new JSONObject();
            head_img1.put("head_img", "http://www.17yxb.cn/image/m2.png");
            JSONObject head_img2 = new JSONObject();
            head_img2.put("head_img", "http://www.17yxb.cn/image/m3.png");
            JSONObject head_img3 = new JSONObject();
            head_img3.put("head_img", "http://www.17yxb.cn/image/m4.png");
            advertiseArray.put(head_img0);
            advertiseArray.put(head_img1);
            advertiseArray.put(head_img2);
            advertiseArray.put(head_img3);
            if (advertiseArray.length()>0){
                Advertisements advertisements = new Advertisements(getActivity(), true, inflaters, 3000);
                View view = advertisements.initView(advertiseArray);
                advertisements.setOnPictureClickListener(new Advertisements.onPictrueClickListener() {
                    @Override
                    public void onClick(int position) {

                    }
                });
                llAdvertiseBoard.addView(view);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
