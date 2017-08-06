package view.lcc.tyzs.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.bean.Address;
import view.lcc.tyzs.bean.CityModel;
import view.lcc.tyzs.bean.CountryModel;
import view.lcc.tyzs.bean.ProvinceModel;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.mvp.presenter.AddressAddPresenter;
import view.lcc.tyzs.mvp.presenter.impl.AddressAddPresenterImpl;
import view.lcc.tyzs.mvp.view.AddressAddView;
import view.lcc.tyzs.utils.SharePreferenceUtil;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-03 08:33
 * Description:  |
 */
public class AddressAddActivity extends BaseActivity implements AddressAddView, View.OnClickListener
        , CompoundButton.OnCheckedChangeListener {
    private AddressAddPresenter addressAddPresenter;
    private PopupWindow provincePopupWindow;
    private PopupWindow cityPopupWindow;
    private PopupWindow areaPopupWindow;

    private View line_province;
    private View line_city;
    private View line_area;

    private TextView tv_province;
    private TextView tv_city;
    private TextView tv_area;

    private EditText et_er, et_phone, et_edit_address;
    private CheckBox cb_select;

    private String isDefault = "否";
    private String AddressXML;
    //地址列表
    private List<ProvinceModel> provinceList;

    private int pPosition;
    private int cPosition;

    private List<String> province_string = new ArrayList<>();
    private List<String> city_string = new ArrayList<>();
    private List<String> area_string = new ArrayList<>();

    private boolean isCity = true;
    private boolean isCounty = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_add);
        addressAddPresenter = new AddressAddPresenterImpl(this);

        line_province = findViewById(R.id.line_province);
        line_city = findViewById(R.id.line_city);
        line_area = findViewById(R.id.line_area);

        tv_province = (TextView) findViewById(R.id.tv_province);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_area = (TextView) findViewById(R.id.tv_area);

        et_er = (EditText) findViewById(R.id.et_edit_linkman);
        et_phone = (EditText) findViewById(R.id.et_edit_phone);
        et_edit_address = (EditText) findViewById(R.id.et_edit_address);
        cb_select = (CheckBox) findViewById(R.id.cb_select);
        cb_select.setOnCheckedChangeListener(this);

        findViewById(R.id.ll_province).setOnClickListener(this);
        findViewById(R.id.ll_city).setOnClickListener(this);
        findViewById(R.id.ll_country).setOnClickListener(this);
        findViewById(R.id.btn_edit_ok).setOnClickListener(this);

        InitData();
    }

    @Override
    public void AddressAddLoading() {
        createDialog(R.string.address_add);
    }

    @Override
    public void AddressAddSuccess(String result) {
        Frame.getInstance().toastPrompt("添加收货地址成功");
        Intent intent = new Intent();
        intent.putExtra("ok", "ok");
        setResult(RESULT_OK, intent);
        finish();
        closeDialog();
    }

    @Override
    public void AddressAddFail(String msg) {
        Frame.getInstance().toastPrompt("添加收货地址失败");
        closeDialog();
    }

    @Override
    public void NetWorkErr(String msg) {
        Frame.getInstance().toastPrompt("网络不太好,去那个稍后再试");
        closeDialog();
    }
    //获取中国省市区信息
    private void InitData() {
        AddressXML = getRawAddress().toString();
        try {
            analysXML(AddressXML);
            if (provinceList != null) {
                tv_province.setText(provinceList.get(0).getProvince());
                tv_city.setText(provinceList.get(0).getCity_list().get(0).getCity());
                tv_area.setText(provinceList.get(0).getCity_list().get(0).getCounty_list().get(0).getCounty());
                //初始化列表下标
                pPosition = 0;
                cPosition = 0;
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public void analysXML(String data) throws XmlPullParserException {
        try {
            ProvinceModel provinceModel = null;
            CityModel cityModel = null;
            CountryModel countyModel = null;
            List<CityModel> cityList = null;
            List<CountryModel> countyList = null;

            InputStream xmlData = new ByteArrayInputStream(data.getBytes("UTF-8"));
            XmlPullParserFactory factory = null;
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser;
            parser = factory.newPullParser();
            parser.setInput(xmlData, "utf-8");
            String currentTag = null;

            String province;
            String city;
            String county;

            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                String typeName = parser.getName();

                if (type == XmlPullParser.START_TAG) {
                    if ("root".equals(typeName)) {
                        provinceList = new ArrayList<ProvinceModel>();
                    } else if ("province".equals(typeName)) {
                        //获取标签里第一个属性,例如<city name="北京市" index="1">中的name属性
                        province = parser.getAttributeValue(0);
                        provinceModel = new ProvinceModel();
                        provinceModel.setProvince(province);
                        cityList = new ArrayList<CityModel>();
                    } else if ("city".equals(typeName)) {
                        city = parser.getAttributeValue(0);
                        cityModel = new CityModel();
                        cityModel.setCity(city);
                        countyList = new ArrayList<CountryModel>();
                    } else if ("area".equals(typeName)) {
                        county = parser.getAttributeValue(0);
                        countyModel = new CountryModel();
                        countyModel.setCounty(county);
                    }
                    currentTag = typeName;
                } else if (type == XmlPullParser.END_TAG) {
                    if ("root".equals(typeName)) {

                    } else if ("province".equals(typeName)) {
                        provinceModel.setCity_list(cityList);
                        provinceList.add(provinceModel);

                    } else if ("city".equals(typeName)) {
                        cityModel.setCounty_list(countyList);
                        cityList.add(cityModel);

                    } else if ("area".equals(typeName)) {
                        countyList.add(countyModel);
                    }
                } else if (type == XmlPullParser.TEXT) {
                    currentTag = null;
                }
                type = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //所在省
    private void createProvincePopupWindow() {
        if (provinceList != null) {
            for (ProvinceModel provinceModel : provinceList) {
                province_string.add(provinceModel.getProvince());
            }

            View view = getLayoutInflater().inflate(R.layout.listview_popup, null);
            provincePopupWindow = createPopupWindow(view, line_province.getWidth(), WRAP_CONTENT);
            ListView listView = (ListView) view.findViewById(android.R.id.list);
            listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), R.layout.item_popup, province_string));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    provincePopupWindow.dismiss();
                    tv_province.setText(province_string.get(position));
                    pPosition = position;
                    if (provinceList.get(position).getCity_list().size() < 1) {
                        tv_city.setText("");
                        tv_area.setText("");
                        isCity = false;
                        isCounty = false;
                    } else {
                        isCity = true;
                        tv_city.setText(provinceList.get(position).getCity_list().get(0).getCity());
                        cPosition = 0;
                        //判断该市下是否有区级或县级
                        if (provinceList.get(position).getCity_list().get(0).getCounty_list().size() < 1) {
                            tv_area.setText("");
                            isCounty = false;
                        } else {
                            isCounty = true;
                            tv_area.setText(provinceList.get(position).getCity_list().get(0).getCounty_list().get(0).getCounty());
                        }
                    }
                }
            });
        }

    }

    //所在市
    private void createCityPopupWindow() {
        if (provinceList != null) {
            final List<CityModel> city_list = provinceList.get(pPosition).getCity_list();
            if (city_list != null) {
                city_string = new ArrayList<>();
                for (CityModel cityModel : city_list) {
                    city_string.add(cityModel.getCity());
                }

                View view = getLayoutInflater().inflate(R.layout.listview_popup, null);
                cityPopupWindow = createPopupWindow(view, line_province.getWidth(), WRAP_CONTENT);
                ListView listView = (ListView) view.findViewById(android.R.id.list);
                listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), R.layout.item_popup, city_string));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        cityPopupWindow.dismiss();
                        tv_city.setText(city_string.get(position));

                        cPosition = position;
                        if (provinceList.get(pPosition).getCity_list().get(position).getCounty_list().size() < 1) {
                            tv_area.setText("");
                            isCounty = false;
                        } else {
                            isCounty = true;
                            tv_area.setText(provinceList.get(pPosition).getCity_list().get(cPosition)
                                    .getCounty_list().get(0).getCounty());
                        }
                    }
                });
            }
        }
    }

    //所在市
    private void createAreaPopupWindow() {
        if (provinceList != null) {
            List<CountryModel> county_list = provinceList.get(pPosition).getCity_list().get(cPosition).getCounty_list();
            if (county_list != null) {
                area_string = new ArrayList<>();
                for (CountryModel city_list : county_list) {
                    area_string.add(city_list.getCounty());
                }

                View view = getLayoutInflater().inflate(R.layout.listview_popup, null);
                areaPopupWindow = createPopupWindow(view, line_province.getWidth(), WRAP_CONTENT);
                ListView listView = (ListView) view.findViewById(android.R.id.list);
                listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), R.layout.item_popup, area_string));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        areaPopupWindow.dismiss();
                        tv_area.setText(area_string.get(position));
                    }
                });
            }
        }
    }


    public StringBuffer getRawAddress() {
        InputStream in = getResources().openRawResource(R.raw.address);
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            br.close();
            isr.close();
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return sb;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            isDefault = "是";
        } else {
            isDefault = "否";
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //所在省
            case R.id.ll_province:
                createProvincePopupWindow();
                provincePopupWindow.showAsDropDown(line_province);
                break;
            //选择城市
            case R.id.ll_city:
                createCityPopupWindow();
                cityPopupWindow.showAsDropDown(line_city);
                break;
            //选择所在区
            case R.id.ll_country:
                createAreaPopupWindow();
                areaPopupWindow.showAsDropDown(line_area);
                break;
            case R.id.btn_edit_ok:
                saveData();
                break;
        }
    }
    private void saveData() {
        if (cb_select.isChecked()) {
            isDefault = "是";
        } else {
            isDefault = "否";
        }

        if (TextUtils.isEmpty(tv_province.getText().toString()) ||
                TextUtils.isEmpty(tv_city.getText().toString()) ||
                TextUtils.isEmpty(tv_area.getText().toString()) ||
                TextUtils.isEmpty(et_edit_address.getText().toString().trim())
                ) {
            Frame.getInstance().toastPrompt("地址信息不完整");
            return;
        }

        if (TextUtils.isEmpty(et_er.getText().toString().trim())) {
            Frame.getInstance().toastPrompt("收件人不能为空");
            return;
        }

        if (TextUtils.isEmpty(et_phone.getText().toString().trim())) {
            Frame.getInstance().toastPrompt("收件电话不能为空");
            return;
        }

        String totalAddress = tv_province.getText().toString()
                + tv_city.getText().toString()
                + tv_area.getText().toString()
                + et_edit_address.getText().toString().trim();
        //开始编辑地址信息
        addressAddPresenter.addressAdd(
                SharePreferenceUtil.getName()
                , totalAddress, et_er.getText().toString().trim()
                , et_phone.getText().toString().trim()
        );
    }



}
