package com.hsfcompany.tzcs.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hsfcompany.tzcs.R;
import com.hsfcompany.tzcs.adapter.DataAdapter;
import com.hsfcompany.tzcs.adapter.HistoryAdapter;
import com.hsfcompany.tzcs.bean.News;
import com.hsfcompany.tzcs.dao.DataManager;
import com.hsfcompany.tzcs.dao.UserInfo;
import com.hsfcompany.tzcs.ui.ResultActivity;
import com.hsfcompany.tzcs.ui.TeBingActivity;
import com.hsfcompany.tzcs.utils.BeanUtil;
import com.hsfcompany.tzcs.view.LoadingLayout;

import java.util.List;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-17 06:38
 * Description:  |
 */
public class HistoryActivity extends AppCompatActivity implements HistoryAdapter.OnItemClickListener {

    private LoadingLayout loadingLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        loadingLayout = (LoadingLayout) findViewById(R.id.loading_layout);
        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(HistoryActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        HistoryAdapter adapter = new HistoryAdapter();
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        loadingLayout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
        List<UserInfo> data = DataManager.getAllData();
        if (data !=null && data.size()>0){
            adapter.bind(data);
            loadingLayout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
        }else {
            loadingLayout.setLoadingLayout(LoadingLayout.NO_DATA);
        }


        findViewById(R.id.lv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(UserInfo data) {
        Intent intent = new Intent(HistoryActivity.this,ResultActivity.class);
        intent.putExtra("data",data);
        startActivity(intent);
    }
}
