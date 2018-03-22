package com.study.xuan.recyclerviewstudy;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ArrayListWrapper<T> extends ArrayList<T> {
    public int maxSize = 0;
    public boolean canReset = true;
    private int lastSize = 0;
    private String key;

    @Override
    public boolean remove(Object o) {
        RecyclerView.ViewHolder vh = (RecyclerView.ViewHolder) o;
        RcyLog.log(key + "移除---【position=" + vh.getAdapterPosition() + "】");
        if (size() > maxSize) {
            maxSize = size();
            canReset = false;
        }
        if (size() == 0) {
            canReset = true;
        }
        return super.remove(o);
    }

    @Override
    public boolean add(T t) {
        RecyclerView.ViewHolder vh = (RecyclerView.ViewHolder) t;
        RcyLog.log(key + "添加---【position=" + vh.getAdapterPosition() + "】");
        if (canReset) {
            if (size() + 1 > lastSize) {
                maxSize = size() + 1;
            }
        }
        return super.add(t);
    }

    @Override
    public void add(int index, T element) {
        RecyclerView.ViewHolder vh = (RecyclerView.ViewHolder) element;
        RcyLog.log(key + "添加---【position=" + vh.getAdapterPosition() + "】");
        super.add(index, element);
    }

    @Override
    public T remove(int index) {
        RecyclerView.ViewHolder vh = (RecyclerView.ViewHolder) get(index);
        RcyLog.log(key + "移除---【position=" + vh.getAdapterPosition() + "】");
        return super.remove(index);
    }

    public void setKey(String key) {
        this.key = key;
    }
}
