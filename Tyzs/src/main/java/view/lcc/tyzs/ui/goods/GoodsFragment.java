package view.lcc.tyzs.ui.goods;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.LeftAdapter;
import view.lcc.tyzs.adapter.RightAdapter;
import view.lcc.tyzs.bean.ShoppingBean;
import view.lcc.tyzs.mvp.presenter.GoodsPresenter;
import view.lcc.tyzs.mvp.presenter.impl.GoodsPresenterImpl;
import view.lcc.tyzs.mvp.view.GoodsView;
import view.lcc.tyzs.utils.ACache;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.view.LoadingLayout;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |07-31 17:03
 * Description:  |
 */
public class GoodsFragment extends Fragment implements GoodsView{
    //左右两侧的布局
    private ListView lv1, lv2;
    private LinearLayout lv1_layout;
    //动态布局
    private LoadingLayout loading_layout;


    //左侧的数据
    private static final String[] dataLeft = {"日常食疗", "体质食疗", "体质理疗", "日用系列", "创业包"};
    private int pageNo = 1;
    private List<ShoppingBean> rightData = new ArrayList<>();
    private GoodsPresenter goodsPresenter;
    private RightAdapter rightAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goods_fragment,null);
        loading_layout = (LoadingLayout) view.findViewById(R.id.loading_layout);
        lv1 = (ListView) view.findViewById(R.id.lv1);
        lv2 = (ListView) view.findViewById(R.id.lv2);
        lv1_layout = (LinearLayout) view.findViewById(R.id.lv_layout);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showPopupWindow();
    }

    /**
     * 返回类型 显示双列的ListView的联动的效果
     */
    public void showPopupWindow() {
        final LeftAdapter leftAdapter = new LeftAdapter(getActivity(), Arrays.asList(dataLeft));
        lv1.setAdapter(leftAdapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getAdapter() instanceof LeftAdapter) {
                    leftAdapter.setSelectItem(position);
                    leftAdapter.notifyDataSetChanged();
                    lv2.setVisibility(View.INVISIBLE);
                    rightData.clear();
                    rightData = new ArrayList<>();
                    String type = dataLeft[position];
                    goodsPresenter = new GoodsPresenterImpl(GoodsFragment.this);
                    goodsPresenter.goods(pageNo + "","100",type);
                }
            }
        });
    }

    @Override
    public void getGoodsLoading() {
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
    }

    @Override
    public void getGoodsSuccess(String result) {
        if (!TextUtils.isEmpty(result)){
           try{
               String data = result.replace("\\", "").replace("\"[", "[").replace("]\"", "]");
               JSONObject jsonObject = new JSONObject(data);
               String result_json = jsonObject.getString("resultjson");
                rightData = GsonUtils.changeGsonToList(result_json, ShoppingBean.class);
               if (lv2.getVisibility() == View.INVISIBLE) {
                   lv2.setVisibility(View.VISIBLE);
                   lv1_layout.getLayoutParams().width = 0;
                   rightAdapter = new RightAdapter(getActivity(), rightData);
               }

               if (rightAdapter != null) {
                   loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
                   lv2.setAdapter(rightAdapter);
                   rightAdapter.notifyDataSetChanged();
                   lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                       @Override
                       public void onItemClick(AdapterView<?> parent,
                                               View view, int position, long id) {
                           ShoppingBean bean = rightData.get(position);
                           Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                           intent.putExtra("bean", bean);
                           startActivity(intent);
                       }
                   });
               }
           }catch (Exception e){
               loading_layout.setLoadingLayout(LoadingLayout.NETWORK_ERROR);
               e.printStackTrace();
           }
        }else {
            //数据为空
            loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
        }
    }

    @Override
    public void getGoodsFail(String msg) {
        loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
    }

    @Override
    public void NetWorkErr(String msg) {
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_ERROR);
    }
}
