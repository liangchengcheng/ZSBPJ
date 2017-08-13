package view.lcc.tyzs.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.DataAdapter;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.bean.News;
import view.lcc.tyzs.utils.BeanUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-11 09:08
 * Description:  |
 */
public class NewsActivity extends BaseActivity implements DataAdapter.OnItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_activity);
        initRecycleView();
        findViewById(R.id.lv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initRecycleView() {
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(NewsActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DataAdapter adapter = new DataAdapter();
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        List<News> data = BeanUtil.getNewsData();
        adapter.bind(data);
    }

    @Override
    public void onItemClick(News data) {
        Intent intent = new Intent(NewsActivity.this, NewsDetailsActivity.class);
        intent.putExtra("data",data);
        startActivity(intent);
    }
}
