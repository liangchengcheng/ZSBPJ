package view.lcc.tyzs.mvp.model;

import view.lcc.tyzs.base.ApiClient;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.ParamsMap;
import view.lcc.tyzs.bean.request.AddressEditRequest;
import view.lcc.tyzs.bean.request.LoginRequest;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;
import view.lcc.tyzs.utils.GsonUtils;
import view.lcc.tyzs.utils.Md5Utils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日20:03:25
 * Description:  地址编辑
 */
public class AddressEditModel {

    public OkHttpRequest addressAdd(String aid,
                                    String phone,
                                    String address,
                                    String addressee,
                                    String aphone,
                                    String isdefault,
                                    ResultCallback<String> callback) {
        AddressEditRequest addressEditRequest = new AddressEditRequest();
        addressEditRequest.setPhone(phone);
        addressEditRequest.setAid(aid);
        addressEditRequest.setAddress(address);
        addressEditRequest.setAddressee(addressee);
        addressEditRequest.setAphone(aphone);
        addressEditRequest.setIsdefault(isdefault);

        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("callValue", GsonUtils.createGsonString(addressEditRequest));

        String timeValue = paramsMap.get("Calldate");
        String url = AppConstants.RequestPath.ADDRESSS_EDIT;
        if (url.endsWith("ashx")) {
            url = url+ "?Calldate=" + timeValue;
        } else {
            url = url+ "&Calldate=" + timeValue;
        }
        return ApiClient.create(url, paramsMap).post(callback);

    }

}
