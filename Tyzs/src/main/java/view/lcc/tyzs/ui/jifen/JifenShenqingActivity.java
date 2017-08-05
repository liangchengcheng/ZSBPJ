package view.lcc.tyzs.ui.jifen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.JifenshenqingAdapter;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.base.BaseApplication;
import view.lcc.tyzs.bean.Note;
import view.lcc.tyzs.view.LoadingLayout;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-04 22:04
 * Description:  |
 */
public class JifenShenqingActivity extends BaseActivity implements View.OnClickListener{

    private JifenshenqingAdapter jifenshenqingAdapter;

    private ListView listview;
    private LoadingLayout loadingLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jifen_shenqing_activity);
        loadingLayout = (LoadingLayout) findViewById(R.id.loading_layout);
        listview = (ListView) findViewById(R.id.lv_shopping_main);
        jifenshenqingAdapter = new JifenshenqingAdapter(JifenShenqingActivity.this);
        listview.setAdapter(jifenshenqingAdapter);
        initData();
    }

    private void initData() {
        loadingLayout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
        List<Note> list = BaseApplication.getDaoSession().getNoteDao().queryBuilder().list();
        if (list != null && list.size() > 0){
            jifenshenqingAdapter.addDate(list);
        }else {
            loadingLayout.setLoadingLayout(LoadingLayout.NO_DATA);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
