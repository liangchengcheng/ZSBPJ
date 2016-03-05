package com.lcc.rx.service;

import com.lcc.bean.News;
import com.lcc.rx.RxService;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxLogin {
    public static Subscription getTeams(String date) {

        Subscription subscription = RxService.getLoginService().updateNews(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<News>() {
                    @Override
                    public void call(News games) {
                        //用bus回调
                        //RxService.getBus().post(new GamesEvent(games, Constant.Result.SUCCESS));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //RxService.getBus().post(new GamesEvent(null, Constant.Result.FAIL));
                    }
                });
        return subscription;
    }

    public static Subscription login(String date) {

        Subscription subscription = RxService.getLoginService().updateNews(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<News>() {
                    @Override
                    public void call(News games) {
                        //用bus回调
                        //RxService.getBus().post(new GamesEvent(games, Constant.Result.SUCCESS));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //RxService.getBus().post(new GamesEvent(null, Constant.Result.FAIL));
                    }
                });
        return subscription;
    }
}