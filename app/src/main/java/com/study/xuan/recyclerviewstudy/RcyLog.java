package com.study.xuan.recyclerviewstudy;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Author : xuan.
 * Date : 18-3-15.
 * Description : the file description
 */
public class RcyLog {
    public static final String BAST_LOG = "RcyLog:";


    public static void log(String log) {
        Log.i(BAST_LOG, log);
    }

    public static void log(TextView tv, String log) {
        if (tv.getText().length() != 0) {
            StringBuilder builder = new StringBuilder(tv.getText());
            builder.append("\n").append(log);
            tv.setText(builder);
        } else {
            StringBuilder builder = new StringBuilder(log);
            tv.setText(builder);
        }
    }

    public static void logPool(RecyclerView rcy) {
        RecyclerView.RecycledViewPool pool = rcy.getRecycledViewPool();
        Log.i(BAST_LOG, "当前pool里存在的Text:" + pool.getRecycledViewCount(0));
        while (logPoolVH(pool) != null) {

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
            Class<?> recyclerClass = Class.forName(recycler.getType().getName());
            Field mAttachedScrap = recyclerClass.getDeclaredField("mAttachedScrap");
            mAttachedScrap.setAccessible(true);
            ArrayList<RecyclerView.ViewHolder> scrapInstance = (ArrayList<RecyclerView.ViewHolder>) mAttachedScrap.get(rcyInstance);
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

    public static void loaAllCache(TextView tv, RecyclerView rcy) {
        try {
            Field mRecycler =
                    Class.forName("android.support.v7.widget.RecyclerView").getDeclaredField("mRecycler");
            mRecycler.setAccessible(true);
            RecyclerView.Recycler recyclerInstance = (RecyclerView.Recycler) mRecycler.get(rcy);

            Class<?> recyclerClass = Class.forName(mRecycler.getType().getName());
            Field mViewCacheMax = recyclerClass.getDeclaredField("mViewCacheMax");
            Field mAttachedScrap = recyclerClass.getDeclaredField("mAttachedScrap");
            Field mChangedScrap = recyclerClass.getDeclaredField("mChangedScrap");
            Field mCachedViews = recyclerClass.getDeclaredField("mCachedViews");
            Field mRecyclerPool = recyclerClass.getDeclaredField("mRecyclerPool");
            mViewCacheMax.setAccessible(true);
            mAttachedScrap.setAccessible(true);
            mChangedScrap.setAccessible(true);
            mCachedViews.setAccessible(true);
            mRecyclerPool.setAccessible(true);

            int mViewCacheSize = (int) mViewCacheMax.get(recyclerInstance);
            ArrayListWrapper mAttached =
                    (ArrayListWrapper<RecyclerView.ViewHolder>) mAttachedScrap.get(recyclerInstance);
            ArrayList<RecyclerView.ViewHolder> mChanged =
                    (ArrayList<RecyclerView.ViewHolder>) mChangedScrap.get(recyclerInstance);
            ArrayListWrapper<RecyclerView.ViewHolder> mCached =
                    (ArrayListWrapper<RecyclerView.ViewHolder>) mCachedViews.get(recyclerInstance);
            RecyclerView.RecycledViewPool recycledViewPool =
                    (RecyclerView.RecycledViewPool) mRecyclerPool.get(recyclerInstance);

            Class<?> recyclerPoolClass = Class.forName(mRecyclerPool.getType().getName());

            StringBuilder builder = new StringBuilder();
            builder.append("当前Attached数量：").append(mAttached.maxSize).append("\n")
//                    .append("当前Changed数量：").append(mChanged.size()).append("\n")
                    .append("当前Cache的数量：").append(mCached.size()).append("\n");
            if (mCached.size() > 0) {
                for (RecyclerView.ViewHolder vh : mCached) {
                    builder.append(vh.getItemViewType() == 1 ? "Image" : "Text").append("-").append(vh.getLayoutPosition()).append("\n");
                }
            }
            builder.append("当前Pool中的TextViewHolder的数量：").append(recycledViewPool.getRecycledViewCount(0)).append("\n");
            builder.append("当前Pool中的ImgViewHolder的数量：").append(recycledViewPool.getRecycledViewCount(1));
            tv.setText(builder);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void logProcess(TextView tv, String log) {
        if (tv.getText().length() != 0) {
            StringBuilder builder = (StringBuilder) tv.getText();
            builder.append("\n").append(log);
            tv.setText(builder);
        } else {
            StringBuilder builder = new StringBuilder(log);
            tv.setText(builder);
        }
    }
}
