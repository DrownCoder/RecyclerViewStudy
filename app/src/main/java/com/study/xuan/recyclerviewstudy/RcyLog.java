package com.study.xuan.recyclerviewstudy;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Author : xuan.
 * Date : 18-3-15.
 * Description : the file description
 */
public class RcyLog {
    public static final String BAST_LOG = "-----------------------";


    public static void log(String log) {
        Log.i(BAST_LOG, log);
    }

    public static void logPool(RecyclerView rcy) {
        RecyclerView.RecycledViewPool pool = rcy.getRecycledViewPool();
        Log.i(BAST_LOG, "当前pool里存在的Text:" + pool.getRecycledViewCount(0));
        while (logPoolVH(pool)!=null) {

        }
    }

    private static Object logPoolVH(RecyclerView.RecycledViewPool pool) {
        RecyclerView.ViewHolder vh = pool.getRecycledView(0);
        if (vh != null) {
            Log.i(BAST_LOG, "Pool缓存中TextViewHolder位置：" + vh.getAdapterPosition());
        }
        return vh;
    }

    public static void logScrapCache(RecyclerView rcy) {
        try {
            Field recycler = Class.forName("android.support.v7.widget.RecyclerView").getDeclaredField("mRecycler");
            recycler.setAccessible(true);
            RecyclerView.Recycler rcyInstance = (RecyclerView.Recycler) recycler.get(rcy);
            Field scrap = rcyInstance.getClass().getDeclaredField("mAttachedScrap");
            scrap.setAccessible(true);
            ArrayList<RecyclerView.ViewHolder> scrapInstance = (ArrayList<RecyclerView.ViewHolder>) scrap.get(rcyInstance);
            Log.i(BAST_LOG, "Scrap缓存中存在个数：" + scrapInstance.size());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void logCache(String key, RecyclerView rcy) {
        try {
            Field recycler = Class.forName("android.support.v7.widget.RecyclerView").getDeclaredField("mRecycler");
            recycler.setAccessible(true);
            RecyclerView.Recycler rcyInstance = (RecyclerView.Recycler) recycler.get(rcy);
            Field cache = rcyInstance.getClass().getDeclaredField("mCachedViews");
            cache.setAccessible(true);
            ArrayList<RecyclerView.ViewHolder> cacheInstance = (ArrayList<RecyclerView.ViewHolder>) cache.get(rcyInstance);
            Log.i(BAST_LOG, key + "Cache缓存中存在个数：" + cacheInstance.size());
            if (cacheInstance.size() > 0) {
                for (RecyclerView.ViewHolder holder : cacheInstance) {
                    if (holder instanceof RcyAdapter.TextViewHolder) {
                        Log.i(BAST_LOG, "Cache缓存中TextViewHolder位置：" + holder.getAdapterPosition());
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
