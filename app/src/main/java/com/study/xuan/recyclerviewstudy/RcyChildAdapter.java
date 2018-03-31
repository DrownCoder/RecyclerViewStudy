package com.study.xuan.recyclerviewstudy;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Author : xuan.
 * Date : 18-3-31.
 * Description : the file description
 */
public class RcyChildAdapter extends RecyclerView.Adapter<RcyChildAdapter.ImageChildViewHolder> {
    private List<Integer> mData;
    private Context mContext;
    private TextView tvCreate;

    public RcyChildAdapter(Context context, List<Integer> mData, TextView tvCreateAndBind) {
        mContext = context;
        this.mData = mData;
        tvCreate = tvCreateAndBind;
    }

    @Override
    public ImageChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RcyLog.log(tvCreate, "onCreate---【ImageChildViewHolder】");
        return new ImageChildViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_child_img, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageChildViewHolder holder, int position) {
        //RcyLog.log(tvCreate, "onBind---【ImageChildViewHolder】");
        holder.iv.setBackgroundColor(Color.parseColor(RcyAdapter.getRandColorCode()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ImageChildViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;

        public ImageChildViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_child);
        }
    }

}
