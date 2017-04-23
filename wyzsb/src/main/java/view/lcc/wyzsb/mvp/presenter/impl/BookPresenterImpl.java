package view.lcc.wyzsb.mvp.presenter.impl;

import android.os.Handler;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.List;

import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.bean.Book;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.BookModel;
import view.lcc.wyzsb.mvp.param.NewsParams;
import view.lcc.wyzsb.mvp.presenter.BookPresenter;
import view.lcc.wyzsb.mvp.view.BookView;
import view.lcc.wyzsb.utils.GsonUtils;
import view.lcc.wyzsb.utils.TimeUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class BookPresenterImpl implements BookPresenter {
    private static final int DEF_DELAY = (int) (1 * 1000);
    private BookModel model;
    private BookView view;

    public BookPresenterImpl(BookView view) {
        this.view = view;
        model = new BookModel();
    }

    private void loadData(final int page, final boolean get_data) {
        if (get_data) {
            view.getLoading();
        }
        final long current_time = TimeUtils.getCurrentTime();
        NewsParams newsParams = new NewsParams();
        newsParams.setPage(page);
        model.getBooks(newsParams, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                if (get_data) {
                    view.getDataFail(ApiException.getApiExceptionMessage(e.getMessage()));
                } else {
                    view.refreshOrLoadFail(ApiException.getApiExceptionMessage(e.getMessage()));
                }
            }

            @Override
            public void onResponse(String response) {
                int delay = 0;
                if (TimeUtils.getCurrentTime() - current_time < DEF_DELAY) {
                    delay = DEF_DELAY;
                }
                updateView(response, delay, page, get_data);
            }
        });
    }

    private void updateView(final String entities, int delay, final int page, final boolean get_data) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(entities);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    if (status == 1) {
                        String result = jsonObject.getString("result");
                        List<Book> books = GsonUtils.fromJsonArray(result, Book.class);
                        if (page == 1) {
                            view.refreshView(books);
                        } else {
                            view.loadMoreView(books);
                        }
                    } else {
                        if (get_data) {
                            view.getDataFail(ApiException.getApiExceptionMessage(""));
                        } else {
                            view.refreshOrLoadFail(ApiException.getApiExceptionMessage(""));
                        }
                    }
                } catch (Exception e) {
                    if (get_data) {
                        view.getDataFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    } else {
                        view.refreshOrLoadFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    }
                    e.printStackTrace();
                }
            }
        }, delay);
    }

    @Override
    public void getData(int page, String options) {
        loadData(page,  true);
    }

    @Override
    public void loadMore(int page, String options) {
        loadData(page,  false);
    }

    @Override
    public void refresh(int page, String options) {
        loadData(1,  false);
    }
}
