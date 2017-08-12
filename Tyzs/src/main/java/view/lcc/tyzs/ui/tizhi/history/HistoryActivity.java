package view.lcc.tyzs.ui.tizhi.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.HistoryAdapter;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.base.BaseApplication;
import view.lcc.tyzs.bean.UserInfo;
import view.lcc.tyzs.ui.tizhi.result.ResultActivity;
import view.lcc.tyzs.view.LoadingLayout;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-17 06:38
 * Description:  |
 */
public class HistoryActivity extends BaseActivity implements HistoryAdapter.OnItemClickListener {

    private LoadingLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        loadingLayout = (LoadingLayout) findViewById(R.id.loading_layout);
        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(HistoryActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        HistoryAdapter adapter = new HistoryAdapter();
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        loadingLayout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
        List<UserInfo> data = BaseApplication.getDaoSession().getUserInfoDao().queryBuilder().list();
        if (data != null && data.size() > 0) {
            adapter.bind(data);
            loadingLayout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
        } else {
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
        Intent intent = new Intent(HistoryActivity.this, ResultActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }
}
