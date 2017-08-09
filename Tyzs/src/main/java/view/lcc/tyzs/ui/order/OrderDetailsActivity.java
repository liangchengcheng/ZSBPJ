package view.lcc.tyzs.ui.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.OrderDetailsAdapter;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.bean.OrderDetails;
import view.lcc.tyzs.mvp.presenter.OrderDetailsPresenter;
import view.lcc.tyzs.mvp.presenter.impl.OrderDetailsPresenterImpl;
import view.lcc.tyzs.mvp.view.OrderDetailsView;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.view.LoadingLayout;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-09 16:46
 * Description:  |
 */
public class OrderDetailsActivity extends BaseActivity implements OrderDetailsView {

    private ListView lv_list;
    private LoadingLayout loadingLayout;

    private List<OrderDetails> list = new ArrayList<>();
    private OrderDetailsAdapter adapter;
    private OrderDetailsPresenter orderDetailsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_activity);
        adapter = new OrderDetailsAdapter(OrderDetailsActivity.this);
        lv_list = (ListView) findViewById(R.id.lv_list);
        loadingLayout = (LoadingLayout) findViewById(R.id.loading_layout);
        orderDetailsPresenter = new OrderDetailsPresenterImpl(this);
        orderDetailsPresenter.OrderDetails(getIntent().getStringExtra("OID"));

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void OrderDetailsLoading() {
        loadingLayout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
    }

    @Override
    public void OrderDetailsSuccess(String result) {
        try {
            JSONObject jsonObjecte = new JSONObject(result);
            String code = jsonObjecte.getString("resultno");
            if (code.equals("000")) {
                String data = result.replace("\\", "").replace("\"[", "[").replace("]\"", "]");
                JSONObject jsonObject = new JSONObject(data);
                String resultJson = jsonObject.getString("resultjson");
                list = GsonUtils.fromJsonArray(resultJson, OrderDetails.class);
                if (list.size() > 0) {
                    adapter.addDate(list);
                    lv_list.setAdapter(adapter);
                    loadingLayout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
                } else {
                    loadingLayout.setLoadingLayout(LoadingLayout.NO_DATA);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OrderDetailsFail(String msg) {
        loadingLayout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
    }

    @Override
    public void NetWorkErr(String msg) {
        loadingLayout.setLoadingLayout(LoadingLayout.NETWORK_ERROR);
    }
}
