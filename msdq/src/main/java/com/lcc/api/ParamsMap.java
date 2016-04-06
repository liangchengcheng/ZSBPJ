package com.lcc.api;

import android.text.TextUtils;
import com.lcc.AppConstants;
import java.util.HashMap;

public class ParamsMap extends HashMap<String, String> {
    public ParamsMap() {
        put(AppConstants.ParamKey.CLIENT_ID_KEY, AppConstants.ParamDefaultValue.CLIENT_ID);
        put(AppConstants.ParamKey.CLIENT_SECRET_KEY, AppConstants.ParamDefaultValue.CLIENT_SECRET);
        put(AppConstants.ParamKey.LANGUAGE_KEY, AppConstants.ParamDefaultValue.LANGUAGE);
    }

    public void put(String key, int value) {
        super.put(key, value + "");
    }

    @Override
    public String put(String key, String value) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value))
            return  "";
        return super.put(key, value);
    }
}
