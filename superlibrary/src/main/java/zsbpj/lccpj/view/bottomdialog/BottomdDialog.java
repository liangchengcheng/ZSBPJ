package zsbpj.lccpj.view.bottomdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import zsbpj.lccpj.R;

public class BottomdDialog {

    private class CustomDialog extends Dialog implements View.OnClickListener {

        private final String TAG=CustomDialog.class.getName();

        private int padding;
        private int icon;
        private LinearLayout container;
        private AdapterView.OnItemSelectedListener onItemSelectedListener;
        private List<Item> items;


        public CustomDialog(Context context) {
            super(context);
            items=new ArrayList<>();
            icon=getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_32_dp);
            padding=getContext().getResources().getDimensionPixelSize(R.dimen.dimen_8_dp);

            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            container=new LinearLayout(getContext());
            container.setLayoutParams(params);
            container.setBackgroundColor(Color.WHITE);
            container.setOrientation(LinearLayout.VERTICAL);
            container.setPadding(0,padding,0,padding);

            ScrollView scrollView=new ScrollView(getContext());
            scrollView.addView(container);

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(scrollView,params);
            setCancelable(true);
            setCanceledOnTouchOutside(true);
            getWindow().setGravity(Gravity.BOTTOM);
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        }

        @Override
        public void onClick(View v) {

        }
    }
}
