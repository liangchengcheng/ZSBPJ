package com.lcc.view.video;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class CustomView extends RelativeLayout{

    final static String MATERIALDESIGNXML = "http://schemas.android.com/apk/res-auto";
    final static String ANDROIDXML = "http://schemas.android.com/apk/res/android";

    public boolean isLastTouch=false;

    boolean animation=false;
    final int disabledBackgroundColor = Color.parseColor("#E2E2E2");
    int beforeBackground;

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled){
            setBackgroundColor(beforeBackground);
        }else {
            setBackgroundColor(disabledBackgroundColor);
        }
        invalidate();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAnimationStart() {
        super.onAnimationStart();
        animation=true;
    }

    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        animation=false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (animation)
            invalidate();
    }
}
