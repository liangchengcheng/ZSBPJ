package view.lcc.wyzsb.base;

public class AppConstants {

    public final static String BASE_IP = "http://114.215.164.168:8080";
    // 乘客端常用地址
    public final static String BASE_URL = BASE_IP ;
    // 会员注册地址
    //================================ 特殊字段 =====================================
    public static final String RESPONSE_CODE = "status";

    public static final String RESPONSE_MSG = "message";

    public static final String RESPONSE_RESULT = "result";

    public final class RequestPath {
        //================================新的接口================================
        //删除订单
        public final static String AddFeedBackService = "/Api/deleteOrderById";
        //订单评价
        public final static String GET_ARTICLE = "/hk/getArticle";
        //投诉司机
        public final static String GET_COMMENT = "/hk/pagedes";

        //获取视频列表
        public final static String GET_VIDEO = "/hk/getVideoInfo";
        //获取getHNews
        public final static String GET_NEWS = "/hk/getHNews";
        //获取连接
        public final static String GET_LINKS = "/hk/getHLink";
        //获取books
        public final static String getArticleById = "/hk/getArticleById";

        //获取新闻的主题
        public final static String getHNewsBody = "/hk/getHNewsBody";



        //获取文章的内容详情
        public final static String GET_BOOKS = "/hk/getBooks";



        //验证码
        public final static String CHECK_VCODE = "/hk/checkVcode";
        //注册
        public final static String SIGN = "/hk/register";
        //登录
        public final static String LOGIN = "/hk/login";
        //注册昵称
        public final static String USERNAME = "/hk/editUserName";

        public final static String GET_VOCODE = "/hk/getHBooks";

    }

    public final class ParamKey {
        public final static String PHONE = "p";

        public final static String PARAMS = "params";

        public final static String PHONE_KEY = "username";

        public final static String PASSWORD_KEY = "pw";

        public final static String V_CODE = "vc";

        public final static String TOKEN = "t";
        //APP版本号码
        public final static String APP_VER = "appVer";
        //设备唯一id
        public final static String DEVICE_ID = "deviceId";
        //token
        public final static String TOKEN_HEAD = "token";
        //平台
        public final static String PLATFROM = "platfrom";
        //市场编号
        public final static String MC = "mc";
    }

    public final class ParamDefaultValue {
        //client_secret
        public final static String CLIENT_SECRET = "38e8c5aet76d5c012e32";
        //client_id
        public final static String CLIENT_ID = "1089857302";
    }

}
