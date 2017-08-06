package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.AddressDeleteRequest;
import view.lcc.tyzs.bean.request.LoginRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.Md5Utils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:  删除我的地址
 */
public class AddressDeleteModel {

    public OkHttpRequest addressDelete(String aid,String phone, ResultCallback<String> callback) {
        AddressDeleteRequest addressDeleteRequest = new AddressDeleteRequest();
        addressDeleteRequest.setPhone(phone);
        addressDeleteRequest.setAid(aid);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(addressDeleteRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.ADDRESSS_DELETE;
        if (url.endsWith("ashx")) {
            url = url+ "?Calldate=" + timeValue;
        } else {
            url = url+ "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);
    }

}
