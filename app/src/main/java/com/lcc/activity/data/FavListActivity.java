package com.lcc.activity.data;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lcc.activity.R;
import com.lcc.adapter.FavoriteTestListAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.utils.CacheHelper;
import com.lcc.view.LoadingLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavListActivity extends BaseActivity {

    private ListView mListView;
    private LoadingLayout mLoadingLayout;
    private List<Test> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViews();
        loadData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean Open() {
        return true;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_fav;
    }

    private void loadData() {
        new LoadFavTestAsyncTask().execute();
    }

    private void createViews() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getColorPrimary());
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mListView = (ListView) findViewById(R.id.listview);
        mLoadingLayout = (LoadingLayout) findViewById(R.id.ly_loading);
        mLoadingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadFavTestAsyncTask().execute();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavListActivity.this, FavContentActivity.class);
                intent.putExtra(FavContentActivity.FAV_KEY, mData.get(position));
                startActivity(intent);
            }
        });
    }

    private class LoadFavTestAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingLayout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
            mListView.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            SharedPreferences sp = CacheHelper.getPreferences(CacheHelper.FAV);
            Map<String, ?> map = sp.getAll();
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                if (entry.getValue() instanceof Integer) {
                    Integer testId = (Integer) entry.getValue();
                    mData.add(readTestCache(testId));
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            FavoriteTestListAdapter mAdapter = new FavoriteTestListAdapter(FavListActivity.this);
            mAdapter.setDatas(mData);
            mListView.setAdapter(mAdapter);
            if (mData.size() != 0) {
                mLoadingLayout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
                mListView.setVisibility(View.VISIBLE);
            } else {
                mLoadingLayout.setLoadingLayout(LoadingLayout.NO_DATA_FAV);
                mListView.setVisibility(View.GONE);
            }
        }
    }

    private Test readTestCache(int testId) {
        Serializable serializable = CacheHelper.readObject(FavListActivity.this, CacheHelper.TEST + testId);
        if (serializable == null) {
            return null;
        } else {
            return (Test) serializable;
        }
    }

}
