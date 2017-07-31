package view.lcc.tyzs.base;

import android.util.Pair;

import java.io.File;
import java.util.List;
import java.util.Map;

import view.lcc.tyzs.frame.okhttp.request.OkHttpRequest;


public class ApiClient {

    public static OkHttpRequest.Builder create(String path, Map paramsMap, Map headerMap) {
        return new OkHttpRequest.Builder()
                .url(AppConstants.BASE_URL + path).params(paramsMap).content(null).headers(headerMap);
    }

    public static OkHttpRequest.Builder create(String path, String content, Map headerMap) {
        return new OkHttpRequest.Builder()
                .url(AppConstants.BASE_URL + path).content(content).headers(headerMap);
    }

    public static OkHttpRequest.Builder create(String path, Map paramsMap) {
        return create(path, paramsMap, new HeaderMap());
    }

    public static OkHttpRequest.Builder create(String path, String content) {
        return create(path, content, new HeaderMap());
    }

    public static OkHttpRequest.Builder create(String path) {
        return new OkHttpRequest.Builder().url(AppConstants.BASE_URL + path).headers(new HeaderMap());
    }

    public static OkHttpRequest.Builder createWithFile(String path, Map paramsMap, List<File> files) {
        Pair[] pairs = new Pair[files.size()];
        for (int i = 0; i < files.size(); i++) {
            Pair pair = new Pair("file", files.get(i));
            pairs[i] = pair;
        }

        return new OkHttpRequest.Builder().url(AppConstants.BASE_URL + path)
                .params(paramsMap).files(pairs).headers(null);
    }

    public static OkHttpRequest.Builder createWithFiles(String path, Map paramsMap, List<File> files) {
        Pair[] pairs = new Pair[files.size()];
        for (int i = 0; i < files.size(); i++) {
            Pair pair = new Pair("file" + i, files.get(i));
            pairs[i] = pair;
        }

        return new OkHttpRequest.Builder().url(AppConstants.BASE_URL + path).params(paramsMap)
                .files(pairs).headers(null);
    }

    public static OkHttpRequest.Builder createWithFileName(String path, Map paramsMap, File file,
                                                           String filename) {
        Pair[] pairs = new Pair[1];
        Pair pair = new Pair(filename, file);
        pairs[0] = pair;

        return new OkHttpRequest.Builder().url(AppConstants.BASE_URL + path)
                .params(paramsMap).files(pairs).headers(null);
    }

    //会员系统
    public static OkHttpRequest.Builder createUser(String path, Map paramsMap, Map headerMap) {
        return new OkHttpRequest.Builder()
                .url(AppConstants.BASE_URL + path).params(paramsMap).content(null).headers(headerMap);
    }

    public static OkHttpRequest.Builder createUser(String path, String content, Map headerMap) {
        return new OkHttpRequest.Builder()
                .url(AppConstants.BASE_URL + path).content(content).headers(headerMap);
    }

    public static OkHttpRequest.Builder createUser(String path, Map paramsMap) {
        return createUser(path, paramsMap, new HeaderMap());
    }

    public static OkHttpRequest.Builder createUser(String path, String content) {
        return createUser(path, content, new HeaderMap());
    }

    public static OkHttpRequest.Builder createPay(String path, Map paramsMap) {
        return createPay(path, paramsMap, new HeaderMap());
    }

    public static OkHttpRequest.Builder createPay(String path, String content) {
        return createPay(path, content, new HeaderMap());
    }

    public static OkHttpRequest.Builder createPay(String path, Map paramsMap, Map headerMap) {
        return new OkHttpRequest.Builder()
                .url(AppConstants.BASE_URL + path).params(paramsMap).content(null).headers(headerMap);
    }

    public static OkHttpRequest.Builder createPay(String path, String content, Map headerMap) {
        return new OkHttpRequest.Builder()
                .url(AppConstants.BASE_URL + path).content(content).headers(headerMap);
    }


}
