package com.lcc.constants;

public class Constant {

    public enum CLASS_TYPE {
        NEWS("news"),BLOG("blog");
        private String newsType;
        CLASS_TYPE(String newsType) {
            this.newsType=newsType;
        }
        public String getNewsType() {
            return newsType;
        }
    }

    public enum GETNEWSWAY {
        INIT,UPDATE,LOADMORE;
    }

    public enum Result {
        SUCCESS,FAIL,NORMAL;
    }

}
