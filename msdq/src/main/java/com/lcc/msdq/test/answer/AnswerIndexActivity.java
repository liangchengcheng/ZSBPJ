package com.lcc.msdq.test.answer;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.lcc.adapter.AnswerIndexAdapter;
import com.lcc.adapter.BaseRecyclerAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.msdq.R;
import com.lcc.view.LoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  AnswerIndexActivity
 */
public class AnswerIndexActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private AnswerIndexAdapter mAdapter;

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AnswerIndexAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addDatas(generateData());
        setHeader(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {

            }
        });
    }

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.activity_answer_header, view, false);
        mAdapter.setHeaderView(header);
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
