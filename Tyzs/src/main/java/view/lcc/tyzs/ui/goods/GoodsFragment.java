package view.lcc.tyzs.ui.goods;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.LeftAdapter;
import view.lcc.tyzs.adapter.RightAdapter;
import view.lcc.tyzs.bean.ShoppingBean;
import view.lcc.tyzs.mvp.presenter.GoodsPresenter;
import view.lcc.tyzs.mvp.presenter.impl.GoodsPresenterImpl;
import view.lcc.tyzs.mvp.view.GoodsView;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.view.LoadingLayout;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |07-31 17:03
 * Description:  |
 */
public class GoodsFragment extends Fragment implements GoodsView {
    //左右两侧的布局
    private ListView lv1, lv2;
    //动态布局
    private LoadingLayout loading_layout;

    //左侧的数据
    private static final String[] dataLeft = {"日常食疗", "体质食疗", "体质理疗", "日用系列", "创业包"};
    private int pageNo = 1;
    private int pageSize = 100;
    private List<ShoppingBean> rightData = new ArrayList<>();
    private GoodsPresenter goodsPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goods_fragment, null);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        goodsPresenter = new GoodsPresenterImpl(GoodsFragment.this);
        loading_layout = (LoadingLayout) view.findViewById(R.id.loading_layout);
        lv1 = (ListView) view.findViewById(R.id.lv1);
        lv2 = (ListView) view.findViewById(R.id.lv2);
        showPopupWindow();
        return view;
    }

    private LeftAdapter leftAdapter;

    /**
     * 返回类型 显示双列的ListView的联动的效果
     */
    public void showPopupWindow() {
        leftAdapter = new LeftAdapter(getActivity(), Arrays.asList(dataLeft));
        leftAdapter.setSelectItem(0);
        lv1.setAdapter(leftAdapter);
        goodsPresenter.goods(pageNo + "", pageSize + "", dataLeft[0]);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getAdapter() instanceof LeftAdapter) {
                    leftAdapter.setSelectItem(position);
                    leftAdapter.notifyDataSetChanged();
                    rightData = new ArrayList<>();
                    String type = dataLeft[position];
                    goodsPresenter.goods(pageNo + "", pageSize + "", type);
                }
            }
        });
    }

    @Override
    public void getGoodsLoading() {
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
    }

    private RightAdapter rightAdapter;

    @Override
    public void getGoodsSuccess(String result) {
        if (!TextUtils.isEmpty(result)) {
            try {
                String data = result.replace("\\", "").replace("\"[", "[").replace("]\"", "]");
                JSONObject jsonObject = new JSONObject(data);
                String result_json = jsonObject.getString("resultjson");
                rightData = GsonUtils.fromJsonArray(result_json, ShoppingBean.class);
                if (rightAdapter == null) {
                    rightAdapter = new RightAdapter(getActivity(), rightData);
                    lv2.setAdapter(rightAdapter);
                } else {
                    rightAdapter.setData(rightData);
                }

                loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
                lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ShoppingBean bean = rightData.get(position);
                        Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                        intent.putExtra("bean", bean);
                        startActivity(intent);
                    }
                });
            } catch (Exception e) {
                loading_layout.setLoadingLayout(LoadingLayout.NETWORK_ERROR);
                e.printStackTrace();
            }
        } else {
            //数据为空
            loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
        }
    }

    @Override
    public void getGoodsFail(String msg) {
        if (msg.equals("116")) {
            loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
        } else {
            loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
        }
    }

    @Override
    public void NetWorkErr(String msg) {
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_ERROR);
    }

    public void onEvent(Integer event) {
        switch (event) {
            case 0x02:
                showPopupWindow();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
