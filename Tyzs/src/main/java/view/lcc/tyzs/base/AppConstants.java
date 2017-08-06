package view.lcc.tyzs.base;

public class AppConstants {

    // 乘客端常用地址
    public final static String BASE_URL = "http://115.28.2.207:8341" ;
    public final static String PIC_URL = "http://115.28.2.207:8003/" ;

    //================================ 特殊字段 =====================================
    public static final String RESPONSE_CODE = "status";

    public static final String RESPONSE_MSG = "message";

    public static final String RESPONSE_RESULT = "result";

    public final class RequestPath {

        //注册
        public final static String SIGN = "/Interface/Register.ashx";
        //登录
        public final static String LOGIN = "/Interface/Login.ashx";

        public final static String GOODS = "/Interface/GetGoodList.ashx?action=all";



        //收货地址 增加
        public final static String SHOP_CAR_ADD = "/Interface/Shopcar.ashx?action=add";

        //收货地址 删除
        public final static String SHOP_CAR_GET = "/Interface/Shopcar.ashx?action=get";






        //收货地址获取
        public final static String ADDRESSS_GET = "/Interface/AddressInfo.ashx?action=get";

        //收货地址 编辑
        public final static String ADDRESSS_EDIT = "/Interface/AddressInfo.ashx?action=change";

        //收货地址 增加
        public final static String ADDRESSS_ADD = "/Interface/AddressInfo.ashx?action=add";

        //收货地址 删除
        public final static String ADDRESSS_DELETE = "/Interface/AddressInfo.ashx?action=delete";






        //积分转账
        public final static String JFZZ = "/Interface/Transfer.ashx";

        //积分提现申请
        public final static String JFTX = "/Interface/DrawCash.ashx";

        //积分转换
        public final static String JFZH = "/Interface/Convert.ashx";

        //积分列表
        public final static String JFLB = "/Interface/Point.ashx?action=getlist";

        //积分余额
        public final static String JFYE = "/Interface/Point.ashx?action=balance";

        //订单生成
        public final static String DDSC = "/Interface/Order.ashx?action=add";

    }

    public final class ParamKey {
        public final static String PHONE = "p";
        //APP版本号码
        public final static String APP_VER = "appVer";
        //设备唯一id
        public final static String DEVICE_ID = "deviceId";
        //token
        public final static String TOKEN_HEAD = "token";
        //平台
        public final static String PLATFROM = "platform";
        //市场编号
        public final static String MC = "mc";
    }

}
