package com.lcc;


public class AppConstants {

    public final class RequestPath {

        public final static String BASE_URL = "http://114.215.164.168:8080";
        /**
         * 首页活动的内容界面
         */
        public final static String ACTIVITY_CONTENT = "http://www.tengxungame.pub:8081/news?mid=";

        public final static String CATEGOTY = "/channels/header_list.json?language=zh-Hans";


        public final static String HOT_VIDEO_LIST = "/hot/feed_timeline.json";
        public final static String MEDIAS = "/medias/show.json";

        public final static String LIKES_VIDEO_CREATE = "/likes/create.json";
        public final static String LIKES_VIDEO_DESTORY = "/likes/destroy.json";

        public final static String COMMENTS = "/comments/show.json";
        public final static String CREATE_COMMENTS_LIKE = "/comments/create_like.json";
        public final static String DESTORY_COMMENT_LIKE = "/comments/destroy_like.json";

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



        public final static String GET_MENU_CONTENT = "/service/getContentByID";

        public final static String SEND_VERIFY_CODE = "/common/send_verify_code_to_phone.json";

        public final static String USERS_UPDATE = "/users/update.json";

        public final static String FRIENDSHIPS = "/friendships/friends.json";

        public final static String FOLLOWERS = "/friendships/followers.json";

        public final static String USER_MEDIAS = "/medias/user_timeline.json";

        public final static String USER_REPOSTS = "/reposts/user_timeline.json";
    }

    public final class ParamKey {
        //client_secret
        public final static String CLIENT_SECRET_KEY = "client_secret";
        //grant_type
        public final static String GRANT_TYPE_KEY = "grant_type";
        //client_id
        public final static String CLIENT_ID_KEY = "client_id";

        public final static String DEVICE_ID_KEY = "device_id";

        public final static String LANGUAGE_KEY = "language";

        public final static String MODEL_KEY = "model";

        public final static String PHONE = "phone";

        public final static String ACCESS_TOKEN_KEY = "access-token";

        public final static String PHONE_KEY = "username";

        public final static String PASSWORD_KEY = "password";

        public final static String ID_KEY = "mid";

        public final static String FID = "fid";

        public final static String TYPE_KEY = "type";

        public final static String PAGE_KEY = "page";

        public final static String COUNT_KEY = "count";

        public final static String VERIFY_CODE_KEY = "verify_code";

        public final static String AUTO_LOGIN_KEY = "auto_login";

        public final static String SCREEN_NAEM_KEY = "screen_name";

        public final static String GENDER_KEY = "gender";

        public final static String DESCRIPTION_KEY = "description";

        public final static String UID_KEY = "uid";

        public final static String WITH_CAPTION_KEY = "with_caption";

        public final static String TOKEN = "token";

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
