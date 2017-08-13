package view.lcc.tyzs.ui.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.CarAdapter;
import view.lcc.tyzs.base.BaseApplication;
import view.lcc.tyzs.base.BaseFragment;
import view.lcc.tyzs.bean.OrderInfo;
import view.lcc.tyzs.bean.ShoppingCarBean;
import view.lcc.tyzs.bean.ShoppingCarItemBean;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.mvp.presenter.ShopCarAddPresenter;
import view.lcc.tyzs.mvp.presenter.ShopCarGetPresenter;
import view.lcc.tyzs.mvp.presenter.impl.ShopCarAddPresenterImpl;
import view.lcc.tyzs.mvp.presenter.impl.ShopCarGetPresenterImpl;
import view.lcc.tyzs.mvp.view.ShopCarAddView;
import view.lcc.tyzs.mvp.view.ShopCarGetView;
import view.lcc.tyzs.ui.goods.OrderConfirmActivity;
import view.lcc.tyzs.ui.login.LoginMainActivity;
import view.lcc.tyzs.utils.DialogUtils;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.SharePreferenceUtil;
import view.lcc.tyzs.view.CartProductItemChangedListener;
import view.lcc.tyzs.view.LoadingLayout;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |07-31 17:04
 * Description:  |
 */
public class CarFragment extends BaseFragment implements CartProductItemChangedListener, View.OnClickListener, ShopCarAddView, ShopCarGetView {

    private ListView listview;
    private LoadingLayout loading_layout;
    private CheckBox allCheck;
    private TextView total_sum;


    private double prince = 0;
    private String Rate;
    private CarAdapter adapter;
    private List<ShoppingCarBean> beans;

    private Map<Integer, ShoppingCarBean> unCheckedList = new HashMap<Integer, ShoppingCarBean>();
    private boolean checkMark = true;
    private DecimalFormat df = new DecimalFormat("######0.00");

    private ShopCarGetPresenter shopCarGetPresenter;
    private ShopCarAddPresenter shopCarAddPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.car_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        loading_layout = (LoadingLayout) view.findViewById(R.id.loading_layout);
        total_sum = (TextView) view.findViewById(R.id.total_sum);
        Rate = SharePreferenceUtil.getRate();
        listview = (ListView) view.findViewById(R.id.lv_main);

        view.findViewById(R.id.moreBtn).setOnClickListener(this);
        view.findViewById(R.id.ok).setOnClickListener(this);
        view.findViewById(R.id.btn_delete).setOnClickListener(this);

        shopCarAddPresenter = new ShopCarAddPresenterImpl(this);
        shopCarGetPresenter = new ShopCarGetPresenterImpl(this);

        adapter = new CarAdapter(getActivity());
        adapter.setCartProductItemChangedListener(this);
        allCheck = (CheckBox) view.findViewById(R.id.cb_cart_all_check);
        allCheck.setOnCheckedChangeListener(new myCheckChangeListener());
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            beans = BaseApplication.getDaoSession().getShoppingCarBeanDao().queryBuilder().list();
            if (beans != null && beans.size() > 0) {
                adapter.newData(beans);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < beans.size(); i++) {
                    unCheckedList.put(i, beans.get(i));
                }
                loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
            } else {
                //没有数据就去服务器获取
                loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void itemNumChanged(int position, int num) {
        prince = 0;
        if (TextUtils.isEmpty(Rate)) {
            for (int i = 0; i < adapter.getCount(); i++) {
                if (adapter.getItem(i).isCheck()) {
                    prince += Double.parseDouble(adapter.getItem(i).getPrice()) * Double.parseDouble(adapter.getItem(i).getNumber());
                }
            }
        } else {
            for (int i = 0; i < adapter.getCount(); i++) {
                if (adapter.getItem(i).isCheck()) {
                    prince += (Double.parseDouble(adapter.getItem(i).getCost())
                            + Double.parseDouble(adapter.getItem(i).getProfit())
                            * Double.parseDouble(Rate))
                            * Double.parseDouble(adapter.getItem(i).getNumber());
                }
            }
        }
        total_sum.setText("合:￥" + df.format(prince).replace("-", ""));
    }

    @Override
    public void itemCheckChanged(int position, boolean isCheck) {
        prince = 0;
        if (isCheck) {
            if (unCheckedList.containsKey(position)) {
                unCheckedList.remove(beans.get(position));
                unCheckedList.keySet().remove(position);
                if (TextUtils.isEmpty(Rate)) {
                    for (int i = 0; i < adapter.getCount(); i++) {
                        if (adapter.getItem(i).isCheck()) {
                            prince += Double.parseDouble(adapter.getItem(i).getPrice()) * Double.parseDouble(adapter.getItem(i).getNumber());
                        }
                    }
                } else {
                    for (int i = 0; i < adapter.getCount(); i++) {
                        if (adapter.getItem(i).isCheck()) {
                            prince += (Double.parseDouble(adapter.getItem(i).getCost())
                                    + Double.parseDouble(adapter.getItem(i).getProfit())
                                    * Double.parseDouble(Rate))
                                    * Double.parseDouble(adapter.getItem(i).getNumber());
                        }
                    }
                }
            }
        } else {
            checkMark = false;
            allCheck.setChecked(false);
            unCheckedList.put(position, beans.get(position));
            checkMark = true;
            if (TextUtils.isEmpty(Rate)) {
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (adapter.getItem(i).isCheck()) {
                        prince -= Double.parseDouble(adapter.getItem(i).getPrice()) * Double.parseDouble(adapter.getItem(i).getNumber());
                    }
                }
            } else {
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (adapter.getItem(i).isCheck()) {
                        prince -= (Double.parseDouble(adapter.getItem(i).getCost())
                                + Double.parseDouble(adapter.getItem(i).getProfit())
                                * Double.parseDouble(Rate))
                                * Double.parseDouble(adapter.getItem(i).getNumber());
                    }
                }
            }
        }
        if (unCheckedList.size() == 0) {
            checkMark = false;
            allCheck.setChecked(true);
            checkMark = true;
            if (TextUtils.isEmpty(Rate)) {
                for (int i = 0; i < adapter.getCount(); i++) {
                    prince += Double.parseDouble(adapter.getItem(i).getPrice())
                            * Double.parseDouble(adapter.getItem(i).getNumber());
                }
            } else {
                for (int i = 0; i < adapter.getCount(); i++) {
                    prince += (Double.parseDouble(adapter.getItem(i).getCost())
                            + Double.parseDouble(adapter.getItem(i).getProfit())
                            * Double.parseDouble(Rate))
                            * Double.parseDouble(adapter.getItem(i).getNumber());
                }
            }
        }
        total_sum.setText("合:￥" + df.format(prince).replace("-", "") );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //删除应该也得同步
            case R.id.btn_delete:
                try {
                    ArrayList<ShoppingCarBean> delete_List = new ArrayList<ShoppingCarBean>();
                    for (ShoppingCarBean bean : adapter.beans) {
                        if (bean.isCheck()) {
                            delete_List.add(bean);
                        }
                    }
                    if (delete_List.size() > 0) {
                        for (ShoppingCarBean shoppingCarBean : delete_List) {
                            BaseApplication.getDaoSession().getShoppingCarBeanDao().delete(shoppingCarBean);
                        }
                    }
                    loading_layout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
                    beans = BaseApplication.getDaoSession().getShoppingCarBeanDao().queryBuilder().list();
                    adapter.newData(beans);
                    allCheck.setChecked(false);
                    total_sum.setText("合:￥ 0"  );
                    for (int i = 0; i < beans.size(); i++) {
                        unCheckedList.put(i, beans.get(i));
                    }
                    if (beans.size() > 0) {
                        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
                    } else {
                       loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //提交购买
            case R.id.ok:
                String name = SharePreferenceUtil.getName();
                if (TextUtils.isEmpty(name)){
                   Frame.getInstance().toastPrompt("请先登录");
                    LoginMainActivity.startLoginMainActivity("buy",getActivity());
                    return;
                }
                ArrayList<OrderInfo> infos = new ArrayList<OrderInfo>();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (adapter.getItem(i).isCheck()) {
                        OrderInfo info = new OrderInfo();
                        info.setGID(adapter.getItem(i).getGID());
                        info.setName(adapter.getItem(i).getName());
                        info.setNumber(adapter.getItem(i).getNumber());
                        if (Rate == null || Rate.equals("")) {
                            info.setTrueprice(adapter.getItem(i).getPrice());
                        } else {
                            double sum = Double.parseDouble(adapter.getItem(i).getCost())
                                    + Double.parseDouble(adapter.getItem(i).getProfit())
                                    * Double.parseDouble(Rate);
                            info.setTrueprice(df.format(sum) + "");
                        }
                        infos.add(info);
                    }
                }

                if (infos.size() < 1){
                    Frame.getInstance().toastPrompt("请选择结算的商品");
                    return;
                }
                //删除已经购买的了（其实这个地方不合理，应该是购买成功了再删除）
                ArrayList<ShoppingCarBean> is = new ArrayList<ShoppingCarBean>();
                for (ShoppingCarBean bean : adapter.beans) {
                    if (bean.isCheck()) {
                        is.add(bean);
                    }
                }
                if (is.size() > 0) {
                    for (ShoppingCarBean shoppingCarBean : is) {
                        BaseApplication.getDaoSession().getShoppingCarBeanDao().delete(shoppingCarBean);
                    }
                }
                //删除后刷新购物车界面
                beans = BaseApplication.getDaoSession().getShoppingCarBeanDao().queryBuilder().list();
                adapter.newData(beans);
                allCheck.setChecked(false);
                total_sum.setText("合:￥ 0"  );
                for (int i = 0; i < beans.size(); i++) {
                    unCheckedList.put(i, beans.get(i));
                }
                if (beans.size() > 0) {
                    loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
                } else {
                    loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
                }
                //已经购买,删除后同步到服务器
                List<ShoppingCarItemBean> data_list = new ArrayList<ShoppingCarItemBean>();
                List<ShoppingCarBean> shoppingCarBeanList = BaseApplication.getDaoSession().getShoppingCarBeanDao().queryBuilder().list();
                for (int i = 0; i < shoppingCarBeanList.size(); i++) {
                    ShoppingCarItemBean s = new ShoppingCarItemBean();
                    s.setGID(shoppingCarBeanList.get(i).getGID());
                    s.setNumber(shoppingCarBeanList.get(i).getNumber());
                    data_list.add(s);
                }
                if (data_list.size() > 0){
                    Gson gsons = new Gson();
                    shopCarAddPresenter.shopCarAdd(name, gsons.toJson(data_list));
                }
                Intent intent = new Intent(getActivity(), OrderConfirmActivity.class);
                intent.putExtra("data", infos);
                intent.putExtra("action", "car");
                startActivity(intent);
                break;
            //同步信息
            case R.id.moreBtn:
                String p = SharePreferenceUtil.getName();
                if (TextUtils.isEmpty(p)){
                    Frame.getInstance().toastPrompt("请先登录");
                    return;
                }
                if (beans.size() > 0){
                    List<ShoppingCarItemBean> list = new ArrayList<ShoppingCarItemBean>();
                    try {
                        List<ShoppingCarBean> shoppingCarBeans = BaseApplication.getDaoSession().getShoppingCarBeanDao().queryBuilder().list();
                        for (int i = 0; i < shoppingCarBeans.size(); i++) {
                            ShoppingCarItemBean s = new ShoppingCarItemBean();
                            s.setGID(shoppingCarBeans.get(i).getGID());
                            s.setNumber(shoppingCarBeans.get(i).getNumber());
                            list.add(s);
                        }
                        if (list.size() < 1){
                            Frame.getInstance().toastPrompt("数据已经同步");
                            return;
                        }
                        String phone = SharePreferenceUtil.getName();
                        if (!TextUtils.isEmpty(phone)) {
                            Gson gson = new Gson();
                            shopCarAddPresenter.shopCarAdd(phone, gson.toJson(list));

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    shopCarGetPresenter.shopCarGet(SharePreferenceUtil.getName());
                }
                break;
        }
    }

    //全选的操作
    private class myCheckChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            prince = 0;
            if (checkMark) {
                for (ShoppingCarBean cProduct : beans) {
                    cProduct.setCheck(isChecked);
                }
                if (isChecked) {
                    unCheckedList.clear();
                    for (int i = 0; i < adapter.getCount(); i++) {
                        if (!TextUtils.isEmpty(Rate)) {
                            prince += (Double.parseDouble(adapter.getItem(i).getCost())
                                    + Double.parseDouble(adapter.getItem(i).getProfit())
                                    * Double.parseDouble(Rate))
                                    * Double.parseDouble(adapter.getItem(i).getNumber());
                        } else {
                            prince += (Double.parseDouble(adapter.getItem(i).getPrice()))
                                    * Double.parseDouble(adapter.getItem(i).getNumber());
                        }
                    }
                    total_sum.setText("合:￥" + df.format(prince).replace("-", ""));
                } else {
                    for (int i = 0; i < beans.size(); i++) {
                        unCheckedList.put(i, beans.get(i));
                    }
                    total_sum.setText("合:￥0");
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void ShopCarAddLoading() {
        createDialog(R.string.send_info);
    }

    @Override
    public void ShopCarAddSuccess(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("加载成功");
    }

    @Override
    public void ShopCarAddFail(String msg) {
        closeDialog();
    }

    @Override
    public void NetWorkErr(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("网络不稳定，请稍后再试");
    }

    @Override
    public void ShopCarGetLoading() {
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
    }

    @Override
    public void ShopCarGetSuccess(String result) {
        try {
            String data = result.replace("\\", "").replace("\"[", "[").replace("]\"", "]");
            JSONObject dataBean = new JSONObject(data);
            String resultJson = dataBean.getString("resultjson");
            beans = GsonUtils.fromJsonArray(resultJson, ShoppingCarBean.class);
            if (beans.size() > 0) {
                for (int i = 0; i < beans.size(); i++) {
                    BaseApplication.getDaoSession().getShoppingCarBeanDao().insertOrReplace(beans.get(i));
                }
                adapter.newData(beans);
                listview.setAdapter(adapter);
                for (int i = 0; i < beans.size(); i++) {
                    unCheckedList.put(i, beans.get(i));
                }
                loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
            }else {
                loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ShopCarGetFail(String msg) {
        if (msg.equals("142")){
            loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
        }else {
            loading_layout.setLoadingLayout(LoadingLayout.LOADDATA_ERROR);
        }
    }

}
