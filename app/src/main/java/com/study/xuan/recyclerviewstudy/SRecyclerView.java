package com.study.xuan.recyclerviewstudy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.lang.reflect.Field;

/**
 * Author : xuan.
 * Date : 18-3-15.
 * Description : the file description
 */
public class SRecyclerView extends RecyclerView {
    public ArrayListWrapper<ViewHolder> mAttachedRecord;
    public ArrayListWrapper<ViewHolder> mCachedRecord;

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
        mAttachedRecord = new ArrayListWrapper<>();
        mCachedRecord = new ArrayListWrapper<>();
        mAttachedRecord.setKey("mAttachedScrap");
        mCachedRecord.setKey("mCachedViews");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        onLayoutListener.beforeLayout();
        super.onLayout(changed, l, t, r, b);
        onLayoutListener.afterLayout();
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }

    public void setAllCache() {
        try {
            Field mRecycler =
                    Class.forName("android.support.v7.widget.RecyclerView").getDeclaredField("mRecycler");
            mRecycler.setAccessible(true);
            RecyclerView.Recycler recyclerInstance =
                    (RecyclerView.Recycler) mRecycler.get(this);

            Class<?> recyclerClass = Class.forName(mRecycler.getType().getName());
            Field mAttachedScrap = recyclerClass.getDeclaredField("mAttachedScrap");
            mAttachedScrap.setAccessible(true);
            mAttachedScrap.set(recyclerInstance, mAttachedRecord);
            Field mCacheViews = recyclerClass.getDeclaredField("mCachedViews");
            mCacheViews.setAccessible(true);
            mCacheViews.set(recyclerInstance, mCachedRecord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
