package com.study.xuan.recyclerviewstudy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Author : xuan.
 * Date : 18-3-15.
 * Description : the file description
 */
public class SRecyclerView extends RecyclerView {
    public interface onLayoutListener {
        void beforeLayout();

        void afterLayout();
    }

    private onLayoutListener onLayoutListener;

    public void setOnLayoutListener(SRecyclerView.onLayoutListener onLayoutListener) {
        this.onLayoutListener = onLayoutListener;
    }

    public SRecyclerView(Context context) {
        this(context, null);
    }

    public SRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        RcyLog.log("onLayout");
        onLayoutListener.beforeLayout();
        super.onLayout(changed, l, t, r, b);
        onLayoutListener.afterLayout();
    }
}
