package view.lcc.tyzs.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import view.lcc.tyzs.R;
import view.lcc.tyzs.ui.jifen.JifenListActivity;
import view.lcc.tyzs.ui.jifen.JifenMainActivity;
import view.lcc.tyzs.ui.login.LoginMainActivity;
import view.lcc.tyzs.ui.order.OrderMainActivity;
import view.lcc.tyzs.ui.setting.HelpActivity;
import view.lcc.tyzs.ui.setting.JIeshaoActivity;
import view.lcc.tyzs.utils.StatusBarUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |07-31 17:03
 * Description:  |
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_fragment, null);
        view.findViewById(R.id.ll_jifen).setOnClickListener(this);
        view.findViewById(R.id.tzcs).setOnClickListener(this);
        view.findViewById(R.id.zchy).setOnClickListener(this);
        view.findViewById(R.id.jfbd).setOnClickListener(this);
        view.findViewById(R.id.cjwt).setOnClickListener(this);
        view.findViewById(R.id.gsjs).setOnClickListener(this);
        view.findViewById(R.id.rxcp).setOnClickListener(this);
        view.findViewById(R.id.gwc).setOnClickListener(this);

        view.findViewById(R.id.tv_order).setOnClickListener(this);
        view.findViewById(R.id.ll_news).setOnClickListener(this);
        view.findViewById(R.id.ll_change_zy).setOnClickListener(this);
        //StatusBarUtil.setStatusBarTranslucent(getActivity(), false);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            //我的积分
            case R.id.ll_jifen:
                intent = new Intent(getActivity(), JifenMainActivity.class);
                startActivity(intent);
                break;
            //我的订单
            case R.id.tv_order:
                intent = new Intent(getActivity(), OrderMainActivity.class);
                startActivity(intent);
                break;
            //我的消息
            case R.id.ll_news:
                intent = new Intent(getActivity(), OrderMainActivity.class);
                startActivity(intent);
                break;
            //我的体质
            case R.id.ll_change_zy:
                intent = new Intent(getActivity(), OrderMainActivity.class);
                startActivity(intent);
                break;



            //体质测试
            case R.id.tzcs:
                intent = new Intent(getActivity(), LoginMainActivity.class);
                startActivity(intent);
                break;
            //注册会员
            case R.id.zchy:
                intent = new Intent(getActivity(), LoginMainActivity.class);
                intent.putExtra("action","register");
                startActivity(intent);
                break;
            //积分变动
            case R.id.jfbd:
                intent = new Intent(getActivity(), JifenListActivity.class);
                startActivity(intent);
                break;
            //常见问题
            case R.id.cjwt:
                intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
                break;
            //公司介绍
            case R.id.gsjs:
                intent = new Intent(getActivity(), JIeshaoActivity.class);
                startActivity(intent);
                break;
            //商品
            case R.id.rxcp:
                ((MainActivity)(getActivity())).setCurrent(1);
                break;
            //购物车
            case R.id.gwc:
                ((MainActivity)(getActivity())).setCurrent(2);
                break;
        }
    }
}
