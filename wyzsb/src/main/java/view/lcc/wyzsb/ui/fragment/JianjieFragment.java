package view.lcc.wyzsb.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.bean.Video;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:
 */
public class JianjieFragment extends Fragment{

    private Video video;

    public JianjieFragment(Video video){
        this.video = video;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jianjie_fragment,null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(video.getV_t());

        TextView tv_author = (TextView) view.findViewById(R.id.tv_author);
        tv_author.setText(video.getV_a());

        TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
        tv_time.setText(video.getV_time());

        TextView tv_js = (TextView) view.findViewById(R.id.tv_js);
        tv_js.setText(video.getV_js());

        return view;
    }
}
