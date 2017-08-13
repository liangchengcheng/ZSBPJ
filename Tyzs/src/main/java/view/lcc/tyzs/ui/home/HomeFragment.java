package view.lcc.tyzs.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.greenrobot.event.EventBus;
import view.lcc.tyzs.R;
import view.lcc.tyzs.ui.jifen.JifenListActivity;
import view.lcc.tyzs.ui.jifen.JifenMainActivity;
import view.lcc.tyzs.ui.login.LoginMainActivity;
import view.lcc.tyzs.ui.order.OrderMainActivity;
import view.lcc.tyzs.ui.setting.HelpActivity;
import view.lcc.tyzs.ui.setting.JIeshaoActivity;
import view.lcc.tyzs.ui.tizhi.TizhiActivity;
import view.lcc.tyzs.ui.tizhi.history.HistoryActivity;
import view.lcc.tyzs.ui.tizhi.main.TanshiTestMainActivity;
import view.lcc.tyzs.utils.SharePreferenceUtil;
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
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        
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
        view.findViewById(R.id.llAdvertiseBoard).setOnClickListener(this);

        view.findViewById(R.id.qt).setOnClickListener(this);
        //StatusBarUtil.setStatusBarTranslucent(getActivity(), false);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        String name = SharePreferenceUtil.getName();
        switch (v.getId()) {
            //我的积分
            case R.id.ll_jifen:
                if (TextUtils.isEmpty(name)){
                    LoginMainActivity.startLoginMainActivity("",getActivity());
                    return;
                }
                intent = new Intent(getActivity(), JifenMainActivity.class);
                startActivity(intent);
                break;
            //我的订单
            case R.id.tv_order:
                if (TextUtils.isEmpty(name)){
                    LoginMainActivity.startLoginMainActivity("",getActivity());
                    return;
                }
                intent = new Intent(getActivity(), OrderMainActivity.class);
                startActivity(intent);
                break;
            //我的消息
            case R.id.ll_news:
                if (TextUtils.isEmpty(name)){
                    LoginMainActivity.startLoginMainActivity("",getActivity());
                    return;
                }
                intent = new Intent(getActivity(), PersonNumActivity.class);
                startActivity(intent);
                break;
            //测试记录
            case R.id.ll_change_zy:
                intent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);
                break;
            //体质测试
            case R.id.llAdvertiseBoard:
                intent = new Intent(getActivity(), TizhiActivity.class);
                startActivity(intent);
                break;
            case R.id.tzcs:
                intent = new Intent(getActivity(), TanshiTestMainActivity.class);
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
                if (TextUtils.isEmpty(name)){
                    LoginMainActivity.startLoginMainActivity("",getActivity());
                    return;
                }
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
                if (TextUtils.isEmpty(name)){
                    LoginMainActivity.startLoginMainActivity("",getActivity());
                    return;
                }
                ((MainActivity)(getActivity())).setCurrent(2);
                break;
            //健康知识
            case R.id.qt:
                startActivity(new Intent(getActivity(),NewsActivity.class));
                break;
        }
    }
    public void onEvent(Integer event) {
        switch (event) {
            case 0x03:
                setData();
                break;
        }
    }

    /**
     * 填充界面
     */
    private void setData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
    
}
