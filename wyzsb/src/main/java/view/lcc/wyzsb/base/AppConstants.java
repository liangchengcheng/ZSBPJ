package view.lcc.wyzsb.base;

public class AppConstants {

    public final static String BASE_IP = "http://114.215.164.168:8080";
    // 乘客端常用地址
    public final static String BASE_URL = BASE_IP ;
    // 会员注册地址
    //================================ 特殊字段 =====================================
    public static final String RESPONSE_CODE = "c";

    public static final String RESPONSE_MSG = "m";

    public static final String RESPONSE_RESULT = "r";

    //存在SharedPreferences中电话号码的key
    public static final String PREF_PHONE = "phone";

    //存在SharedPreferences中Token的key
    public static final String USER_TOKEN = "token";
    //微信支付的标记
    public static final int WEIXIN_PAY = 101;
    //支付宝支付的标记
    public static final int ALI_PAY = 102;
    //================================ 微信支付的参数 =====================================
    //微信支付的 AppId
    public static final String Wechat_APP_ID = "ssd78sd8sd8sd7sd78sd";

    public static final String Wechat_MCH_ID = "未知";

    public static final String Wechat_PACKAGE_VALUE = "Sign=WXPay";

    public static final String Wechat_KEY = "";

    //================================ 支付宝支付的参数 =====================================
    public static final String ALIPAY_APP_ID = "2016121204156910";

    public static final String ALIPAY_PAY = "alipay.trade.app.pay";

    public static final String ALIPAY_NOTIFY_HTTPS = "alipay.trade.app.pay";

    //================================ 其他的参数 =====================================
    public static final int SDK_PAY_FLAG = 1;

    public static final int SDK_AUTH_FLAG = 2;

    public static final String TOKEN_INVALID = "token_invalid";

    public final class RequestPath {
        //================================ 登录注册相关 ===============================
        //注册账号
        public final static String OAUTH = "/API/register";
        //检测手机号是否注册
        public final static String CHECK_PHONE = "/Api/checkPhone";
        //获取apk版本更新信息
        public final static String GET_UPDATE_APK = "/Api/appUpdate";
        //删除常用地址
        public final static String DEL_ADDRESS = "/Api/delAddresses/";
        //设置常用地址
        public final static String SET_ADDRESS = "/Api/setAddresses";
        //获取常用地址
        public final static String GET_ADDRESS = "/Api/getAddresses/";
        //登录接口
        public final static String LOGIN = "/API/checkLogin";
        //发送验证码
        public final static String REQUEST_CODE = "/API/sendVcode";
        //校验验证码
        public final static String CHECK_VCODE = "/API/checkVcode";
        //设置密码（注册）
        public final static String SET_PASSWORD = "/Api/register";
        //退出账号（废弃）
        public final static String SIGN_OUT = "/Api/loyout";

        //================================投诉评价订单================================
        //删除订单
        public final static String AddFeedBackService = "/Api/deleteOrderById";
        //订单评价
        public final static String GET_ARTICLE = "/service/pagedes";
        //投诉司机
        public final static String GET_COMMENT = "/service/pagedes";


        //=============================  网约车的  ================================
        //网约车的  正在进行  的车费详情
        public final static String WYC_ORDER_DETAIL_A = "/Api/wyc/getOrderDetails/A";
        //网约车的  等待出发  的车费详情
        public final static String WYC_ORDER_DETAIL_B = "/Api/wyc/OrderDetails/b/";
        //网约车的  已经完成  已经支付  的车费详情
        public final static String WYC_ORDER_DETAIL_C = "/Api/wyc/getOrderDetail/c/";
        //网约车的  已经完成  未支付    的车费详情
        public final static String WYC_ORDER_DETAIL_D = "/Api/wyc/getOrderDetail/d/";
        //网约车的  已经取消  已经违约  已经支付  的车费详情
        public final static String WYC_ORDER_DETAIL_E = "/Api/wyc/getOrderDetail/e/";
        //网约车的  已经取消  已经违约  未支付  的车费详情
        public final static String WYC_ORDER_DETAIL_F = "/Api/wyc/getOrderDetail/f/";
        //网约车的  已经取消  未违约    的车费详情
        public final static String WYC_ORDER_DETAIL_G = "/Api/wyc/getOrderDetail/g/";
        //网约车的车费详情
        public final static String MONEY_DETAILS_WYC = "/Api/wyc/moneyDetails/";
        //迅游车的车费详情
        public final static String MONEY_DETAILS_XYC = "/Api/monecyDetailsXyc";

        //================================打车过程===================================
        //车费预估
        public final static String GET_PRICE = "/Api/getPrice";
        //呼叫巡游车生成订单
        public final static String SEND_TAXI_ORDER = "/Api/sendXyTaxiOrder";
        //呼叫网约车订单
        public final static String SEND_CAR_ORDER = "/Api/sendWyTaxiOrder";
        //获取附近车辆位置信息
        public final static String GET_CARS_LOCATION = "/Api/getCarsLocations";
        //生成订单后的车辆信息
        public final static String GET_ORDER_CAR_LOCATION = "/Api/getOrderCarsLocations";
        //接单的巡游车的车辆信息
        public final static String GET_ORDER_TAXI_INFO = "/Api/getXyTaxiCarInfo";
        //接单的网约车的车辆信息
        public final static String GET_ORDER_CAR_INFO = "/Api/getWyTaxiCarInfo";
        //乘客取消订单
        public final static String CANCEL_ORDER = "/Api/cancelOrder";
        public final static String CANCEL_ORDERL = "/Api/cancelOrderTime";

        //=============================== 获取基本信息等 ==============================
        //获取系统消息（正在进行、未支付等）
        public final static String GetMainDetails = "/Api/pendingOrder";
        //获取当前司机的位置
        public final static String GetNowPosition = "/Api/getWyTaxiCarFare";
        //获取用户的身份信息
        public final static String GET_USER_DETAILS = "/Api/info/";
        //编辑用户信息
        public final static String EDIT_USER_INFO = "/Api/editmyInfo";
        //编辑常用地址
        public final static String ModifyAddresses = "/Api/modifyAddresses";
        //获取盐
        public final static String GET_SALT = "/API/salt";
        //设置密码
        public final static String S_P_W = "/API/setPassword";
        //修改密码
        public final static String CHANGE_PASS_WORD = "/API/changePassword";
        //获取key
        public final static String GET_KEY = "/API/pubkey";
        //修改手机号
        public final static String CHANGE_PHONE = "/Api/updatePhone";


        //司机抢单成功（推送）
        public final static String COMPETE_ORDER = "/API/Order/competeOrder";
        //开始行程（推送）
        public final static String START_ORDER = "/API/Order/startOrder";
        //订单生成后的车的位置信息
        public final static String AFTER_LOCATION = "/Api/getOrderCarsLocations";
        //图片上传
        public final static String UPLOAD_IMAGE = "/Api/uploadPhoto";
        //统一下单
        public final static String WENCHAT_GET = "/Api/pay/confirmPay";
        //微信支付查询
        public final static String GET_RESULT = "/Api/pay/orderquery";
        //阿里支付状态查询
        public final static String AlipayQuery = "/Api/pay/alipayQuery";
        //获取违约的条件
        public final static String queryDefaultReason = "/Api/queryDefaultReason";
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
