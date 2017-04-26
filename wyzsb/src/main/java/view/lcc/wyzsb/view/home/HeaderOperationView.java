package view.lcc.wyzsb.view.home;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.adapter.home.HeaderOperationAdapter;
import view.lcc.wyzsb.bean.model.OperationEntity;
import view.lcc.wyzsb.ui.activity.article.ArticleActivity;
import view.lcc.wyzsb.utils.ToastUtil;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderOperationView extends AbsHeaderView<List<OperationEntity>> {

    private FixedGridView gvOperation;
    private Activity activity;

    public HeaderOperationView(Activity context) {
        super(context);
        this.activity = context;
    }

    @Override
    protected void getView(List<OperationEntity> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_operation_layout, listView, false);

        gvOperation = (FixedGridView) view.findViewById(R.id.gv_operation);
        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<OperationEntity> list) {
        if (list == null || list.size() < 2 || list.size() > 6) return;
        if (list.size() % 2 != 0) return;

        final HeaderOperationAdapter adapter = new HeaderOperationAdapter(mActivity, list);
        gvOperation.setAdapter(adapter);

        gvOperation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show(mActivity, adapter.getItem(position).getTitle());
                ArticleActivity.startArticleActivity(activity, adapter.getItem(position).getTitle());
            }
        });
    }

}
