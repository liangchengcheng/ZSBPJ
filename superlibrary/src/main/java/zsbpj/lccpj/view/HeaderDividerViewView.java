package zsbpj.lccpj.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import zsbpj.lccpj.R;

/**
 * listview加一个headerView
 */
public class HeaderDividerViewView extends HeaderViewInterface<String> {

    public HeaderDividerViewView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(String s, ListView listView) {
        View view = mInflate.inflate(R.layout.header_divider_layout, listView, false);
        listView.addHeaderView(view);
    }

}
