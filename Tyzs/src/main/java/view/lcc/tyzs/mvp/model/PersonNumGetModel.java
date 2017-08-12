package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.AddressAddRequest;
import view.lcc.tyzs.bean.request.GetPersonNumRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:  |获取自己的下级人数
 */
public class PersonNumGetModel {

    public OkHttpRequest PersonNumGet(String phone, ResultCallback<String> callback) {
        GetPersonNumRequest getPersonNumRequest = new GetPersonNumRequest();
        getPersonNumRequest.setPhone(phone);


        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(getPersonNumRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.PERSON_NUM;
        if (url.endsWith("ashx")) {
            url = url+ "?Calldate=" + timeValue;
        } else {
            url = url+ "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
