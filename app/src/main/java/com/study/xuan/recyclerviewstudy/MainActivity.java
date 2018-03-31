package com.study.xuan.recyclerviewstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SRecyclerView rcy;
    private RcyAdapter mAdapter;
    private ArrayList<RcyModel> mData;
    private TextView tvCacheView;
    private TextView tvCreateAndBind;
    private ScrollView scrollView;
    private boolean isText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOperate();
        rcy = findViewById(R.id.rcy);
        tvCacheView = findViewById(R.id.tv_cache_view);
        tvCreateAndBind = findViewById(R.id.tv_create_and_bind);
        scrollView = findViewById(R.id.scroll);
        mData = new ArrayList<>();
        mAdapter = new RcyAdapter(mData, this, rcy, tvCreateAndBind);
        rcy.setAdapter(mAdapter);
        rcy.setOnLayoutListener(new SRecyclerView.onLayoutListener() {
            @Override
            public void beforeLayout() {
                rcy.setAllCache();
            }

            @Override
            public void afterLayout() {
                RcyLog.loaAllCache(tvCacheView, rcy);
            }
        });
        rcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //RcyLog.logScrapCache(recyclerView);
                //RcyLog.logCache("onScroll时：", recyclerView);
                //RcyLog.logPool(recyclerView);
                RcyLog.loaAllCache(tvCacheView, rcy);
                scrollView.scrollBy(dx, dy);
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(this));
    }

    private void packData() {
        for (int i = 0; i < 100; i++) {
            RcyModel model = new RcyModel();
            if (isText) {
                model.type = 0;
            } else {
                if (i % 15 == 0) {
                    model.childData = new ArrayList<>();
                    for (int j = 0; j < 7; j++) {
                        model.childData.add(j);
                    }
                    model.type = 2;
                } else {
                    if (i % 2 == 0) {
                        model.type = 0;
                    } else {
                        model.type = 1;
                    }
                }
            }
            model.title = "这是第" + i + "个";
            mData.add(model);
        }
    }

    private void initOperate() {
        TextView refresh = findViewById(R.id.refresh);
        TextView delete = findViewById(R.id.delete);
        TextView change = findViewById(R.id.change);
        refresh.setOnClickListener(this);
        delete.setOnClickListener(this);
        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refresh:
                //mAdapter.notifyDataSetChanged();
                mAdapter.notifyItemChanged(0);
                break;
            case R.id.delete:
                mData.remove(0);
                mAdapter.notifyItemRemoved(0);
                //mAdapter.notifyDataSetChanged();
                break;
            case R.id.change:
                v.setSelected(!v.isSelected());
                isText = v.isSelected();
                mData.clear();
                packData();
                mAdapter.notifyDataSetChanged();
                break;
        }
    }
}
