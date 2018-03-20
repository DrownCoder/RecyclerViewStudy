package com.study.xuan.recyclerviewstudy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Author : xuan.
 * Date : 18-3-14.
 * Description : the file description
 */
public class RcyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RcyModel> mData;
    private Context mContext;
    int textCount;
    int imgCount;
    private RecyclerView mRcy;

    public RcyAdapter(List<RcyModel> mData, Context mContext, RecyclerView recyclerView) {
        this.mData = mData;
        this.mContext = mContext;
        this.mRcy = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            textCount++;
            return new TextViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            RcyLog.logCache("onBind时：", mRcy);
            RcyLog.logPool(mRcy);
            TextViewHolder holder1 = (TextViewHolder) holder;
            holder1.tv.setText(mData.get(position).title);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).type;
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public TextViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
