package com.lcc.msdq.index;

import android.content.Intent;
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
import com.lcc.entity.ActivityEntity;
import com.lcc.frame.Advertisements;
import com.lcc.frame.update.UpdateApkTask;
import com.lcc.msdq.R;
import com.lcc.msdq.index.IndexWebView.IndexWebView;
import com.lcc.mvp.presenter.IndexPresenter;
import com.lcc.mvp.presenter.LoginPresenter;
import com.lcc.mvp.presenter.impl.IndexPresenterImpl;
import com.lcc.mvp.presenter.impl.LoginPresenterImpl;
import com.lcc.mvp.view.IndexView;
import com.lcc.mvp.view.LoginView;
import com.lcc.view.menu.GuillotineAnimation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import zsbpj.lccpj.frame.FrameManager;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2016年04月21日07:17:52
 * Description: 第一页fragment
 */
public class IndexFragment extends BaseFragment implements IndexView {

    private LinearLayout llAdvertiseBoard;
    private LayoutInflater inflaters;
    private FrameLayout root;
    private static final long RIPPLE_DURATION = 250;
    private ImageView iv_menu;
    private IndexPresenter mPresenter;

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
        mPresenter = new IndexPresenterImpl(this);
        mPresenter.getActivity();
        return view;
    }

    @Override
    public void getLoginFail(String msg) {
        FrameManager.getInstance().toastPrompt(msg);
    }

    @Override
    public void getSuccess(final List<ActivityEntity> list) {
        try {
            if (list!=null&&list.size()>0){
                Advertisements advertisements = new Advertisements(getActivity(), true, inflaters, 3000);
                View view = advertisements.initView(list);
                advertisements.setOnPictureClickListener(new Advertisements.onPictrueClickListener() {
                    @Override
                    public void onClick(int position) {
//                        Intent intent=new Intent(getActivity(),IndexContentActivity.class);
//                        intent.putExtra(IndexContentActivity.KEY_URL,list.get(position).getMid());
//                        intent.putExtra(IndexContentActivity.IMAGE_URL,list.get(position).getActivity_pic());
//                        startActivity(intent);

                        Intent intent=new Intent(getActivity(),IndexWebView.class);
                        intent.putExtra(IndexWebView.KEY_URL,list.get(position).getMid());
                        intent.putExtra(IndexWebView.IMAGE_URL,list.get(position).getActivity_pic());
                        startActivity(intent);
                    }
                });
                llAdvertiseBoard.addView(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
