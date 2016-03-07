package com.lcc.entity;

/**
 * Created by lcc on 16/3/8.
 */
public class TestEntity {

    /**
     * recommend_caption : 是谁不得恶心死啊
     * recommend_cover_pic : http://mvimg2.meitudata.com/56dd4436cef853618.jpg!thumb320
     * recommend_cover_pic_size : 320*426
     * media : {"id":486193869,"caption":"送给路人\u201c神秘大礼\u201d","weibo_share_caption":"Check out @杀马特黑牛 \u2019s Meipai:\u201c送给路人\u201c神秘大礼\u201d\u201d","facebook_share_caption":"","weixin_share_caption":"送给路人\u201c神秘大礼\u201d","weixin_friendfeed_share_caption":"Sharing 杀马特黑牛\u2019s video \u201c送给路人\u201c神秘大礼\u201d\u201d, come and take a look!","qzone_share_caption":"Sharing 杀马特黑牛\u2019s video \u201c送给路人\u201c神秘大礼\u201d\u201d, come and take a look!","qq_share_caption":"Sharing 杀马特黑牛\u2019s video \u201c送给路人\u201c神秘大礼\u201d\u201d, come and take a look!","instagram_share_caption":"Sharing 杀马特黑牛\u2019s video \u201c送给路人\u201c神秘大礼\u201d\u201d, come and take a look!","geo":null,"video":"http://mvvideo2.meitudata.com/56dd7f56d1a542964.mp4","url":"http://www.meipai.com/media/486193869","cover_pic":"http://mvimg1.meitudata.com/56dbde3b8cedf1187.jpg!thumb320","pic_size":"480*640","category":3,"time":191,"is_long":true,"show_controls":true,"created_at":"1457249851","comments_count":188,"likes_count":4233,"reposts_count":20,"is_popular":false,"user":{"id":1046584834,"screen_name":"杀马特黑牛","country":2630000,"province":2631200,"city":2631203,"avatar":"http://mvavatar2.meitudata.com/56b22d145a6339172.jpg","gender":"m","birthday":"2016-03-02","age":0,"constellation":"Pisces","verified":false,"followers_count":55392,"friends_count":8,"reposts_count":0,"videos_count":69,"real_videos_count":69,"photos_count":0,"locked_videos_count":0,"real_locked_videos_count":0,"locked_photos_count":0,"be_liked_count":168852,"url":"http://www.meipai.com/user/1046584834","created_at":1442543777,"has_password":false,"status":"5","is_funy_core_user":false,"funy_core_user_created_at":0},"feed_id":6258840454779550413,"locked":false,"caption_url_params":null,"privacy_config":{"allow_save_medias":0},"has_watermark":1,"display_source":1}
     * type : media
     */

    private String recommend_caption;
    private String recommend_cover_pic;
    private String recommend_cover_pic_size;
    /**
     * id : 486193869
     * caption : 送给路人“神秘大礼”
     * weibo_share_caption : Check out @杀马特黑牛 ’s Meipai:“送给路人“神秘大礼””
     * facebook_share_caption :
     * weixin_share_caption : 送给路人“神秘大礼”
     * weixin_friendfeed_share_caption : Sharing 杀马特黑牛’s video “送给路人“神秘大礼””, come and take a look!
     * qzone_share_caption : Sharing 杀马特黑牛’s video “送给路人“神秘大礼””, come and take a look!
     * qq_share_caption : Sharing 杀马特黑牛’s video “送给路人“神秘大礼””, come and take a look!
     * instagram_share_caption : Sharing 杀马特黑牛’s video “送给路人“神秘大礼””, come and take a look!
     * geo : null
     * video : http://mvvideo2.meitudata.com/56dd7f56d1a542964.mp4
     * url : http://www.meipai.com/media/486193869
     * cover_pic : http://mvimg1.meitudata.com/56dbde3b8cedf1187.jpg!thumb320
     * pic_size : 480*640
     * category : 3
     * time : 191
     * is_long : true
     * show_controls : true
     * created_at : 1457249851
     * comments_count : 188
     * likes_count : 4233
     * reposts_count : 20
     * is_popular : false
     * user : {"id":1046584834,"screen_name":"杀马特黑牛","country":2630000,"province":2631200,"city":2631203,"avatar":"http://mvavatar2.meitudata.com/56b22d145a6339172.jpg","gender":"m","birthday":"2016-03-02","age":0,"constellation":"Pisces","verified":false,"followers_count":55392,"friends_count":8,"reposts_count":0,"videos_count":69,"real_videos_count":69,"photos_count":0,"locked_videos_count":0,"real_locked_videos_count":0,"locked_photos_count":0,"be_liked_count":168852,"url":"http://www.meipai.com/user/1046584834","created_at":1442543777,"has_password":false,"status":"5","is_funy_core_user":false,"funy_core_user_created_at":0}
     * feed_id : 6258840454779550413
     * locked : false
     * caption_url_params : null
     * privacy_config : {"allow_save_medias":0}
     * has_watermark : 1
     * display_source : 1
     */

    private MediaEntity media;
    private String type;

    public void setRecommend_caption(String recommend_caption) {
        this.recommend_caption = recommend_caption;
    }

    public void setRecommend_cover_pic(String recommend_cover_pic) {
        this.recommend_cover_pic = recommend_cover_pic;
    }

    public void setRecommend_cover_pic_size(String recommend_cover_pic_size) {
        this.recommend_cover_pic_size = recommend_cover_pic_size;
    }

    public void setMedia(MediaEntity media) {
        this.media = media;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecommend_caption() {
        return recommend_caption;
    }

    public String getRecommend_cover_pic() {
        return recommend_cover_pic;
    }

    public String getRecommend_cover_pic_size() {
        return recommend_cover_pic_size;
    }

    public MediaEntity getMedia() {
        return media;
    }

    public String getType() {
        return type;
    }

    public static class MediaEntity {
        private int id;
        private String caption;
        private String weibo_share_caption;
        private String facebook_share_caption;
        private String weixin_share_caption;
        private String weixin_friendfeed_share_caption;
        private String qzone_share_caption;
        private String qq_share_caption;
        private String instagram_share_caption;
        private Object geo;
        private String video;
        private String url;
        private String cover_pic;
        private String pic_size;
        private int category;
        private int time;
        private boolean is_long;
        private boolean show_controls;
        private String created_at;
        private int comments_count;
        private int likes_count;
        private int reposts_count;
        private boolean is_popular;
        /**
         * id : 1046584834
         * screen_name : 杀马特黑牛
         * country : 2630000
         * province : 2631200
         * city : 2631203
         * avatar : http://mvavatar2.meitudata.com/56b22d145a6339172.jpg
         * gender : m
         * birthday : 2016-03-02
         * age : 0
         * constellation : Pisces
         * verified : false
         * followers_count : 55392
         * friends_count : 8
         * reposts_count : 0
         * videos_count : 69
         * real_videos_count : 69
         * photos_count : 0
         * locked_videos_count : 0
         * real_locked_videos_count : 0
         * locked_photos_count : 0
         * be_liked_count : 168852
         * url : http://www.meipai.com/user/1046584834
         * created_at : 1442543777
         * has_password : false
         * status : 5
         * is_funy_core_user : false
         * funy_core_user_created_at : 0
         */

        private UserEntity user;
        private long feed_id;
        private boolean locked;
        private Object caption_url_params;
        /**
         * allow_save_medias : 0
         */

        private PrivacyConfigEntity privacy_config;
        private int has_watermark;
        private int display_source;

        public void setId(int id) {
            this.id = id;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public void setWeibo_share_caption(String weibo_share_caption) {
            this.weibo_share_caption = weibo_share_caption;
        }

        public void setFacebook_share_caption(String facebook_share_caption) {
            this.facebook_share_caption = facebook_share_caption;
        }

        public void setWeixin_share_caption(String weixin_share_caption) {
            this.weixin_share_caption = weixin_share_caption;
        }

        public void setWeixin_friendfeed_share_caption(String weixin_friendfeed_share_caption) {
            this.weixin_friendfeed_share_caption = weixin_friendfeed_share_caption;
        }

        public void setQzone_share_caption(String qzone_share_caption) {
            this.qzone_share_caption = qzone_share_caption;
        }

        public void setQq_share_caption(String qq_share_caption) {
            this.qq_share_caption = qq_share_caption;
        }

        public void setInstagram_share_caption(String instagram_share_caption) {
            this.instagram_share_caption = instagram_share_caption;
        }

        public void setGeo(Object geo) {
            this.geo = geo;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setCover_pic(String cover_pic) {
            this.cover_pic = cover_pic;
        }

        public void setPic_size(String pic_size) {
            this.pic_size = pic_size;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public void setIs_long(boolean is_long) {
            this.is_long = is_long;
        }

        public void setShow_controls(boolean show_controls) {
            this.show_controls = show_controls;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public void setLikes_count(int likes_count) {
            this.likes_count = likes_count;
        }

        public void setReposts_count(int reposts_count) {
            this.reposts_count = reposts_count;
        }

        public void setIs_popular(boolean is_popular) {
            this.is_popular = is_popular;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public void setFeed_id(long feed_id) {
            this.feed_id = feed_id;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }

        public void setCaption_url_params(Object caption_url_params) {
            this.caption_url_params = caption_url_params;
        }

        public void setPrivacy_config(PrivacyConfigEntity privacy_config) {
            this.privacy_config = privacy_config;
        }

        public void setHas_watermark(int has_watermark) {
            this.has_watermark = has_watermark;
        }

        public void setDisplay_source(int display_source) {
            this.display_source = display_source;
        }

        public int getId() {
            return id;
        }

        public String getCaption() {
            return caption;
        }

        public String getWeibo_share_caption() {
            return weibo_share_caption;
        }

        public String getFacebook_share_caption() {
            return facebook_share_caption;
        }

        public String getWeixin_share_caption() {
            return weixin_share_caption;
        }

        public String getWeixin_friendfeed_share_caption() {
            return weixin_friendfeed_share_caption;
        }

        public String getQzone_share_caption() {
            return qzone_share_caption;
        }

        public String getQq_share_caption() {
            return qq_share_caption;
        }

        public String getInstagram_share_caption() {
            return instagram_share_caption;
        }

        public Object getGeo() {
            return geo;
        }

        public String getVideo() {
            return video;
        }

        public String getUrl() {
            return url;
        }

        public String getCover_pic() {
            return cover_pic;
        }

        public String getPic_size() {
            return pic_size;
        }

        public int getCategory() {
            return category;
        }

        public int getTime() {
            return time;
        }

        public boolean isIs_long() {
            return is_long;
        }

        public boolean isShow_controls() {
            return show_controls;
        }

        public String getCreated_at() {
            return created_at;
        }

        public int getComments_count() {
            return comments_count;
        }

        public int getLikes_count() {
            return likes_count;
        }

        public int getReposts_count() {
            return reposts_count;
        }

        public boolean isIs_popular() {
            return is_popular;
        }

        public UserEntity getUser() {
            return user;
        }

        public long getFeed_id() {
            return feed_id;
        }

        public boolean isLocked() {
            return locked;
        }

        public Object getCaption_url_params() {
            return caption_url_params;
        }

        public PrivacyConfigEntity getPrivacy_config() {
            return privacy_config;
        }

        public int getHas_watermark() {
            return has_watermark;
        }

        public int getDisplay_source() {
            return display_source;
        }

        public static class UserEntity {
            private int id;
            private String screen_name;
            private int country;
            private int province;
            private int city;
            private String avatar;
            private String gender;
            private String birthday;
            private int age;
            private String constellation;
            private boolean verified;
            private int followers_count;
            private int friends_count;
            private int reposts_count;
            private int videos_count;
            private int real_videos_count;
            private int photos_count;
            private int locked_videos_count;
            private int real_locked_videos_count;
            private int locked_photos_count;
            private int be_liked_count;
            private String url;
            private int created_at;
            private boolean has_password;
            private String status;
            private boolean is_funy_core_user;
            private int funy_core_user_created_at;

            public void setId(int id) {
                this.id = id;
            }

            public void setScreen_name(String screen_name) {
                this.screen_name = screen_name;
            }

            public void setCountry(int country) {
                this.country = country;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public void setConstellation(String constellation) {
                this.constellation = constellation;
            }

            public void setVerified(boolean verified) {
                this.verified = verified;
            }

            public void setFollowers_count(int followers_count) {
                this.followers_count = followers_count;
            }

            public void setFriends_count(int friends_count) {
                this.friends_count = friends_count;
            }

            public void setReposts_count(int reposts_count) {
                this.reposts_count = reposts_count;
            }

            public void setVideos_count(int videos_count) {
                this.videos_count = videos_count;
            }

            public void setReal_videos_count(int real_videos_count) {
                this.real_videos_count = real_videos_count;
            }

            public void setPhotos_count(int photos_count) {
                this.photos_count = photos_count;
            }

            public void setLocked_videos_count(int locked_videos_count) {
                this.locked_videos_count = locked_videos_count;
            }

            public void setReal_locked_videos_count(int real_locked_videos_count) {
                this.real_locked_videos_count = real_locked_videos_count;
            }

            public void setLocked_photos_count(int locked_photos_count) {
                this.locked_photos_count = locked_photos_count;
            }

            public void setBe_liked_count(int be_liked_count) {
                this.be_liked_count = be_liked_count;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public void setHas_password(boolean has_password) {
                this.has_password = has_password;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setIs_funy_core_user(boolean is_funy_core_user) {
                this.is_funy_core_user = is_funy_core_user;
            }

            public void setFuny_core_user_created_at(int funy_core_user_created_at) {
                this.funy_core_user_created_at = funy_core_user_created_at;
            }

            public int getId() {
                return id;
            }

            public String getScreen_name() {
                return screen_name;
            }

            public int getCountry() {
                return country;
            }

            public int getProvince() {
                return province;
            }

            public int getCity() {
                return city;
            }

            public String getAvatar() {
                return avatar;
            }

            public String getGender() {
                return gender;
            }

            public String getBirthday() {
                return birthday;
            }

            public int getAge() {
                return age;
            }

            public String getConstellation() {
                return constellation;
            }

            public boolean isVerified() {
                return verified;
            }

            public int getFollowers_count() {
                return followers_count;
            }

            public int getFriends_count() {
                return friends_count;
            }

            public int getReposts_count() {
                return reposts_count;
            }

            public int getVideos_count() {
                return videos_count;
            }

            public int getReal_videos_count() {
                return real_videos_count;
            }

            public int getPhotos_count() {
                return photos_count;
            }

            public int getLocked_videos_count() {
                return locked_videos_count;
            }

            public int getReal_locked_videos_count() {
                return real_locked_videos_count;
            }

            public int getLocked_photos_count() {
                return locked_photos_count;
            }

            public int getBe_liked_count() {
                return be_liked_count;
            }

            public String getUrl() {
                return url;
            }

            public int getCreated_at() {
                return created_at;
            }

            public boolean isHas_password() {
                return has_password;
            }

            public String getStatus() {
                return status;
            }

            public boolean isIs_funy_core_user() {
                return is_funy_core_user;
            }

            public int getFuny_core_user_created_at() {
                return funy_core_user_created_at;
            }
        }

        public static class PrivacyConfigEntity {
            private int allow_save_medias;

            public void setAllow_save_medias(int allow_save_medias) {
                this.allow_save_medias = allow_save_medias;
            }

            public int getAllow_save_medias() {
                return allow_save_medias;
            }
        }
    }
}
