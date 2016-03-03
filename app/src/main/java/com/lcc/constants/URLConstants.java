package com.lcc.constants;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:   AppConstants
 */
public class URLConstants {

    /**
     * 请求地址
     */
    public final class RequestPath {
        public final static String BASE_URL = "https://newapi.meipai.com";
    }

    /**
     * 请求的参数
     */
    public final class ParamKey {
        //client_secret
        public final static String CLIENT_SECRET_KEY = "client_secret";
        //grant_type
        public final static String GRANT_TYPE_KEY = "grant_type";
        //client_id
        public final static String CLIENT_ID_KEY = "client_id";
    }

    public final class ParamDefaultValue {

        //client_secret
        public final static String CLIENT_SECRET = "38e8c5aet76d5c012e32";
        //client_id
        public final static String CLIENT_ID = "1089857302";

        public final static String LANGUAGE = "zh-Hans";

        //grant_type
        public final static String GRANT_TYPE = "phone";

        public final static String TYPR_RESET_PASSWORD = "reset_password";
        public final static int AUTO_LOGIN = 1;

        public final static int WITH_CAPTION = 1;

    }

}
