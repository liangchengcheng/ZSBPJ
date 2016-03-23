package com.lcc.activity.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lcc.activity.R;
import com.lcc.activity.question.QuestionMainActivity;
import com.lcc.frame.Advertisements;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import zsbpj.lccpj.frame.FrameManager;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:  在线课程
 */
public class HomeFragment extends Fragment {

    private LinearLayout llAdvertiseBoard;
    private LayoutInflater inflaters;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        inflaters = LayoutInflater.from(getActivity());
        llAdvertiseBoard = (LinearLayout) view.findViewById(R.id.llAdvertiseBoard);
        initViews();
        view.findViewById(R.id.ll_xx).setOnClickListener(new BtnListener());
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Advertisements advertisements = new Advertisements(getActivity(), true, inflaters, 3000);
        View view = advertisements.initView(advertiseArray);
        advertisements.setOnPictureClickListener(new Advertisements.onPictrueClickListener() {
            @Override
            public void onClick(int position) {
                FrameManager.getInstance().toastPrompt("点击的是" + position);
            }
        });
        llAdvertiseBoard.addView(view);
    }

    public class BtnListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_xx:
                    startActivity(new Intent(getActivity(), QuestionMainActivity.class));
                    break;
            }
        }
    }
}
