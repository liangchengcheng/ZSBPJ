package com.lcc;


public class AppConstants {

    public final class RequestPath {

        public final static String BASE_URL = "http://114.215.164.168:8080";

        /**
         * 首页活动的内容界面
         */
        public final static String ACTIVITY_CONTENT = "http://www.tengxungame.pub:8081/news?mid=";

        /**
         * 注册账号
         */
        public final static String OAUTH = "/service/register";

        /**
         * 登录账号
         */
        public final static String LOGIN = "/service/login";

        /**
         * 初始化密码，重置密码。
         */
        public final static String RESET_PASSWORD = "/service/reset";

        /**
         * 获取试题的列表
         */
        public final static String TEST_LIST = "/pagetestforAndroid";

        /**
         * 获取试题的列表
         */
        public final static String TEST_ANSWER_LIST = "/pageanswerforAndroid";

        /**
         * 获公司面试资料的答案的列表
         */
        public final static String COMPANY_ANSWER_LIST = "/service/getTestanswer";
        /**
         * 获取活动数据
         */
        public final static String GET_ACTIVITY = "/service/getactivity";

        /**
         * 每周精选的数据
         */
        public final static String GET_WEEKDATA = "/service/getWeekData";

        /**
         * 每周精选的数据
         */
        public final static String ARTICLE_DATA = "/service/pageArticleforAndroid";

        /**
         * 我的粉丝
         */
        public final static String getWhoLikeMeList = "/service/getWhoLikeMeList";

        /**
         * 我关注的人
         */
        public final static String getILikeWhoList = "/service/getILikeWhoList";

        /**
         * 获取试题的列表
         */
        public final static String COMPANY_DES = "/service/pagedes";

        /**
         * 获取活动内容
         */
        public final static String GET_ACTIVITY_CONTENT = "/service/getactivitycontentforad";

        /**
         * 获取公司的面试资料
         */
        public final static String JS_TEST = "/service/pageCompanyTestforAndroid";

        /**
         * 收藏的具体功能的url
         */
        public final static String UserFavAdd = "/service/UserFavAdd";

        /**
         * 取消收藏的具体功能的url
         */
        public final static String DDELETEFAV = "/service/deleteFav";

        /**
         * 获取所有评论的url
         */
        public final static String COMMENTS_URL = "/service/getCommentsbyId";

        /**
         * 获取文章具体内容的url
         */
        public final static String GET_MENU_CONTENT = "/service/getContentByID";

        public final static String SEND_VERIFY_CODE = "/common/send_verify_code_to_phone.json";

        /**
         * 发布评论
         */
        public final static String CommentsAdd = "/service/CommentsAdd";

    }

    public final class ParamKey {

        //grant_type
        public final static String GRANT_TYPE_KEY = "grant_type";
        //client_id
        public final static String CLIENT_ID_KEY = "client_id";

        public final static String PHONE = "phone";

        public final static String PHONE_KEY = "username";

        public final static String PASSWORD_KEY = "password";

        public final static String ID_KEY = "mid";

        public final static String NID = "nid";

        public final static String FID = "fid";

        public final static String TYPE_KEY = "type";

        public final static String PAGE_KEY = "page";

        public final static String VERIFY_CODE_KEY = "verify_code";

        public final static String AUTO_LOGIN_KEY = "auto_login";

        public final static String TOKEN = "token";

        public final static String AUTHOR = "author";

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

    }

}
