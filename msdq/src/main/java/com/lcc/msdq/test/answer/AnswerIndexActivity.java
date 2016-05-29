package com.lcc.msdq.test.answer;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.lcc.adapter.AnswerIndexAdapter;
import com.lcc.adapter.BaseRecyclerAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.msdq.R;
import com.lcc.view.LoadMoreRecyclerView;
import com.lcc.view.StretchyTextView;

import java.util.ArrayList;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  AnswerIndexActivity
 */
public class AnswerIndexActivity extends BaseActivity {

    private LoadMoreRecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private AnswerIndexAdapter mAdapter;
    private StretchyTextView spreadTextView;

    @Override
    protected void initView() {
        mRecyclerView = (LoadMoreRecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AnswerIndexAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bind(generateData());
        RecyclerViewHeader header = (RecyclerViewHeader) findViewById(R.id.header);
        spreadTextView = (StretchyTextView) findViewById(R.id.spread_textview);
        spreadTextView.setMaxLineCount(3);
        spreadTextView.setContent("近些年来，越来越多的行业开始和互联网结合，诞生了越来越多的互联网创业公司。" +
                "互联网创业公司需要面对许多的不确定因素。如果你和你的小伙伴们够幸运，你们的公司可能会在几个星期之内让用户数、商品数" +
                "、订单量增长几十倍上百倍。一次促销可能会带来平时几十倍的访问流量，" +
                "一次秒杀活动可能会吸引平时数百倍的访问用户。这对公司自然是极大的好事，说明产品得到认可，公司未来前景美妙。");
        header.attachTo(mRecyclerView);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_answer_index;
    }

    private ArrayList<String> generateData() {
        ArrayList<String> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lists.add(i+"");
        }
        return lists;
    }
}
