package com.lcc.mvp.model;


import com.lcc.AppConstants;
import com.lcc.api.ApiClient;
import com.lcc.api.ParamsMap;
import com.lcc.entity.Replay;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.frame.net.okhttp.request.OkHttpRequest;

import java.io.File;
import java.util.List;

public class ComQuesAddModel {

    /**
     * 发送公司相关的问题到服务器
     */
    public OkHttpRequest ComQuesAdd(Replay replay, List<File>files, ResultCallback<String> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put("content", replay.getContent());
        paramsMap.put("author", replay.getAuthor());
        paramsMap.put("nid", replay.getNid());
        paramsMap.put("pid", replay.getPid());
        paramsMap.put("replay_author", replay.getReplay_author());
        return ApiClient.createWithFile(AppConstants.RequestPath.CommentsAdd, paramsMap,files)
                .tag("")
                .get(callback);
    }
}
