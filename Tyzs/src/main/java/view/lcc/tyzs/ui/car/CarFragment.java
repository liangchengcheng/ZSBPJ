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
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.SharePreferenceUtil;
import view.lcc.tyzs.view.CartProductItemChangedListener;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |07-31 17:04
 * Description:  |
 */
public class CarFragment extends Fragment implements CartProductItemChangedListener, View.OnClickListener, ShopCarAddView, ShopCarGetView {

    private CarAdapter adapter;
    private ListView listview;
    private List<ShoppingCarBean> beans;
    private CheckBox allCheck;
    private String Rate;
    private TextView total_sum;
    private double prince = 0;

    private Map<Integer, ShoppingCarBean> unCheckedList = new HashMap<Integer, ShoppingCarBean>();
    private boolean checkMark = true;

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

        try {
            String name = SharePreferenceUtil.getName();
            if (TextUtils.isEmpty(name)) {
                Frame.getInstance().toastPrompt("请先登录");
                return;
            }
            beans = BaseApplication.getDaoSession().getShoppingCarBeanDao().queryBuilder().list();
            if (beans != null && beans.size() > 0) {
                adapter.addDate(beans);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < beans.size(); i++) {
                    unCheckedList.put(i, beans.get(i));
                }
            } else {
                //没有数据就去服务器获取
                shopCarGetPresenter.shopCarGet(SharePreferenceUtil.getName());
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
        DecimalFormat df = new DecimalFormat("######0.00");
        total_sum.setText("合：" + df.format(prince).replace("-", "") + "元");
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
        DecimalFormat df = new DecimalFormat("######0.00");
        total_sum.setText("合：" + df.format(prince).replace("-", "") + "元");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

                    beans = BaseApplication.getDaoSession().getShoppingCarBeanDao().queryBuilder().list();
                    if (beans.size() > 0) {
                        adapter.newData(beans);
                        for (int i = 0; i < beans.size(); i++) {
                            unCheckedList.put(i, beans.get(i));
                        }
                    } else {
                        listview.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.ok:
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
                            DecimalFormat df = new DecimalFormat("######0.00");
                            info.setTrueprice(df.format(sum) + "");
                        }
                        infos.add(info);
                    }
                }
                //删除
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

                List<ShoppingCarItemBean> data_list = new ArrayList<ShoppingCarItemBean>();
                List<ShoppingCarBean> shoppingCarBeanList = BaseApplication.getDaoSession().getShoppingCarBeanDao().queryBuilder().list();
                for (int i = 0; i < shoppingCarBeanList.size(); i++) {
                    ShoppingCarItemBean s = new ShoppingCarItemBean();
                    s.setGID(shoppingCarBeanList.get(i).getGID());
                    s.setNumber(shoppingCarBeanList.get(i).getNumber());
                    data_list.add(s);
                }

                String name = SharePreferenceUtil.getName();
                if (!TextUtils.isEmpty(name)) {
                    Gson gson = new Gson();
                    shopCarAddPresenter.shopCarAdd(name, gson.toJson(data_list));
                }

                Intent intent = new Intent(getActivity(), OrderConfirmActivity.class);
                intent.putExtra("bean", infos);
                startActivity(intent);
                break;
            //同步信息
            case R.id.moreBtn:
                List<ShoppingCarItemBean> list = new ArrayList<ShoppingCarItemBean>();
                try {
                    List<ShoppingCarBean> shoppingCarBeans = BaseApplication.getDaoSession().getShoppingCarBeanDao().queryBuilder().list();
                    for (int i = 0; i < shoppingCarBeans.size(); i++) {
                        ShoppingCarItemBean s = new ShoppingCarItemBean();
                        s.setGID(shoppingCarBeans.get(i).getGID());
                        s.setNumber(shoppingCarBeans.get(i).getNumber());
                        list.add(s);
                    }

                    String phone = SharePreferenceUtil.getName();
                    if (!TextUtils.isEmpty(phone)) {
                        Gson gson = new Gson();
                        shopCarAddPresenter.shopCarAdd(phone, gson.toJson(list));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
                    DecimalFormat df = new DecimalFormat("######0.00");
                    total_sum.setText("合：" + df.format(prince).replace("-", "") + "元");
                } else {
                    for (int i = 0; i < beans.size(); i++) {
                        unCheckedList.put(i, beans.get(i));
                    }
                    total_sum.setText("合：0元");
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void ShopCarAddLoading() {
        Frame.getInstance().toastPrompt("正在提交购物车信息");
    }

    @Override
    public void ShopCarAddSuccess(String msg) {
        Frame.getInstance().toastPrompt("同步购物车信息成功");
    }

    @Override
    public void ShopCarAddFail(String msg) {
        Frame.getInstance().toastPrompt("同步购物车信息失败");
    }

    @Override
    public void NetWorkErr(String msg) {
        Frame.getInstance().toastPrompt("网络不稳定，请稍后再试");
    }

    @Override
    public void ShopCarGetLoading() {
        Frame.getInstance().toastPrompt("正在获取信息...");
    }

    @Override
    public void ShopCarGetSuccess(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            String data = result.replace("\\", "").replace("\"[", "[").replace("]\"", "]");
            JSONObject dataBean = new JSONObject(data);
            String resultJson = dataBean.getString("resultjson");
            beans = GsonUtils.fromJsonArray(resultJson, ShoppingCarBean.class);
            if (beans.size() > 0) {
                for (int i = 0; i < beans.size(); i++) {
                    BaseApplication.getDaoSession().getShoppingCarBeanDao().insert(beans.get(i));
                }
                adapter.addDate(beans);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < beans.size(); i++) {
                    unCheckedList.put(i, beans.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ShopCarGetFail(String msg) {

    }

}
