package com.lcc.mvp;


import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.entity.AnswerAdd;
import com.lcc.entity.TestEntity;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;

import java.io.File;
import java.util.List;

public class TestAnswerAddModel {

    /**
     * 发送公司相关的问题到服务器
     */
    public OkHttpRequest TestAnswerAdd(AnswerAdd replay, List<File> files, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("answer", replay.getAnswer());
        paramsMap.put("fid", replay.getFid());
        return ApiClient.createWithFile(AppConstants.RequestPath.answerAddservice,
                paramsMap, files)
                .upload(callback);
    }
}
