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
import java.util.Random;

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
    private TextView tvCreateAndBind;

    public RcyAdapter(List<RcyModel> mData, Context mContext, RecyclerView recyclerView, TextView tvCreateAndBind) {
        this.mData = mData;
        this.mContext = mContext;
        this.mRcy = recyclerView;
        this.tvCreateAndBind = tvCreateAndBind;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            textCount++;
            RcyLog.log(tvCreateAndBind, "onCreate---【TextViewHolder】");
            return new TextViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false));
        } else if (viewType == 1) {
            RcyLog.log(tvCreateAndBind, "onCreate---【ImageViewHolder】");
            return new ImageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof TextViewHolder) {
            //RcyLog.logCache("onBind时：", mRcy);
            //RcyLog.logPool(mRcy);
            RcyLog.log(tvCreateAndBind, "Text=onBind---【position=" + position + "】");
            TextViewHolder holder1 = (TextViewHolder) holder;
            holder1.tv.setText(mData.get(position).title);
            holder1.tv.setBackgroundColor(Color.parseColor(getRandColorCode()));
        } else if (holder instanceof ImageViewHolder) {
            RcyLog.log(tvCreateAndBind, "Img=onBind---【position=" + position + "】");
            ImageViewHolder holder2 = (ImageViewHolder) holder;
            holder2.tv.setText(mData.get(position).title);
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

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;

        public ImageViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    /**
     * 获取十六进制的颜色代码.例如 "#6E36B4" , For HTML ,
     *
     * @return String
     */
    public static String getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return "#" + r + g + b;
    }
}
