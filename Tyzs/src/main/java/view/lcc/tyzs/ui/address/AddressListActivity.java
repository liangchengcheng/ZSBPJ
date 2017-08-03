package view.lcc.tyzs.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.AddressListAdapter;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.bean.Address;
import view.lcc.tyzs.mvp.presenter.AddressGetPresenter;
import view.lcc.tyzs.mvp.view.AddressGetView;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-02 22:23
 * Description:  |
 */
// TODO: 2017/8/3 登录放在外侧
public class AddressListActivity extends BaseActivity implements AddressListAdapter.EditListener
        ,AdapterView.OnItemClickListener  ,View.OnClickListener,AddressGetView{
    private ListView addressList;
    private AddressListAdapter addressListAdapter;
    private AddressGetPresenter presenter;

    private List<Address> list = new ArrayList<Address>();
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_list);
        initView();
    }

    private void initView() {
        addressList = (ListView) findViewById(R.id.lv_ad_list);
        addressList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        findViewById(R.id.iv_more).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    private void getAddress(){
        String phone = SharePreferenceUtil.getName();
        presenter.addressGet(phone);
    }

    @Override
    public void edit(Address address) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //判断是不是从结账的地方选择的
        String action = getIntent().getStringExtra("action");
        if (action != null && action.equals("confirm")) {
            Intent intent = new Intent();
            intent.putExtra("address",list.get(position));
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_more:
                Intent intent = new Intent(AddressListActivity.this, AddressAddActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void AddressGetLoading() {

    }

    @Override
    public void AddressGetSuccess(String msg) {

    }

    @Override
    public void AddressGetFail(String msg) {

    }

    @Override
    public void NetWorkErr(String msg) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            list = new ArrayList<>();
            //重新获取地址
            getAddress();
        }
    }
}
