package com.lcc.activity.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcc.activity.R;
import com.lcc.activity.information.InformationMainActivity;
import com.lcc.activity.question.QuestionMainActivity;
import com.lcc.frame.Advertisements;
import com.lcc.utils.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
    private TextView tv_count;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        inflaters = LayoutInflater.from(getActivity());
        llAdvertiseBoard = (LinearLayout) view.findViewById(R.id.llAdvertiseBoard);
        tv_count= (TextView) view.findViewById(R.id.tv_count);
        initViews();
        view.findViewById(R.id.ll_xx).setOnClickListener(new BtnListener());
        view.findViewById(R.id.cd_bk).setOnClickListener(new BtnListener());
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
                        FrameManager.getInstance().toastPrompt("点击的是" + position);
                    }
                });
                llAdvertiseBoard.addView(view);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        updateTextView();
    }

    public class BtnListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.ll_xx:
                    startActivity(new Intent(getActivity(), QuestionMainActivity.class));
                    break;

                case R.id.cd_bk:
                    startActivity(new Intent(getActivity(), InformationMainActivity.class));
                    break;
            }
        }
    }

    private void updateTextView() {
        try {
            long current = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = sdf.parse(sdf.format(current));
            Date d2 = sdf.parse("2016-09-15 00:00:00");
            String time_str = DateUtils.daysBetween(d1, d2) + "";
            tv_count.setText("剩余: "+time_str+"天");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
