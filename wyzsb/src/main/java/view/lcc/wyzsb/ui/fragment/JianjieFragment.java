package view.lcc.wyzsb.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.adapter.CommentAdapter;
import view.lcc.wyzsb.bean.Comments;
import view.lcc.wyzsb.bean.Video;
import view.lcc.wyzsb.bean.Videofav;
import view.lcc.wyzsb.frame.Frame;
import view.lcc.wyzsb.mvp.presenter.VideoDetailsPresenter;
import view.lcc.wyzsb.mvp.presenter.impl.VideoDetailsPresenterImpl;
import view.lcc.wyzsb.mvp.view.VideoDetailsView;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class JianjieFragment extends Fragment implements View.OnClickListener, VideoDetailsView {

    private Video video;

    private TextView tv_sc;

    private VideoDetailsPresenter videoDetailsPresenter;

    public JianjieFragment(Video video){
        this.video = video;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jianjie_fragment,null);
        videoDetailsPresenter = new VideoDetailsPresenterImpl(this);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(video.getV_t());

        TextView tv_author = (TextView) view.findViewById(R.id.tv_author);
        tv_author.setText("作者："+video.getV_a());

        TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
        tv_time.setText("时长："+video.getV_time());

        TextView tv_js = (TextView) view.findViewById(R.id.tv_js);
        tv_js.setText(video.getV_js());
        tv_sc = (TextView) view.findViewById(R.id.tv_sc);
        tv_sc.setOnClickListener(this);
        videoDetailsPresenter.getData(video.getId());
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_sc:
                if (isFav){
                    videoDetailsPresenter.deleteFav(video.getId());
                    return;
                }else {
                    videoDetailsPresenter.favVideo(video);
                }
                break;
        }
    }

    @Override
    public void getDataFail(String msg) {
        Frame.getInstance().toastPrompt("获取详情失败");
    }

    private boolean isFav = false;

    @Override
    public void getDataSuccess(Videofav msg) {
        videoDetailsPresenter.historyVideo(video);
        if (msg != null){
            isFav = true;
            tv_sc.setText("已收藏");
        }else {
            isFav = false;
            tv_sc.setText("点击收藏");
        }
    }

    @Override
    public void LookHistory() {
        // 记录+1
    }

    @Override
    public void LookHistoryFail(String msg) {
        //记录+1失败
    }

    @Override
    public void FavSuccess() {
        Frame.getInstance().toastPrompt("收藏成功");
        isFav = true;
        tv_sc.setText("已收藏");
    }

    @Override
    public void FavFail(String msg) {
        Frame.getInstance().toastPrompt("收藏失败");
        isFav = false;
        tv_sc.setText("点击收藏");
    }

    @Override
    public void UnFavSuccess() {

    }

    @Override
    public void UnFavFail(String msg) {

    }


}
