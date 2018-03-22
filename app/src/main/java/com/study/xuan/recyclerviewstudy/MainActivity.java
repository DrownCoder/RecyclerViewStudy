package com.study.xuan.recyclerviewstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SRecyclerView rcy;
    private RcyAdapter mAdapter;
    private ArrayList<RcyModel> mData;
    private TextView tvCacheView;
    private TextView tvCreateAndBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOperate();
        rcy = findViewById(R.id.rcy);
        tvCacheView = findViewById(R.id.tv_cache_view);
        tvCreateAndBind = findViewById(R.id.tv_create_and_bind);
        tvCreateAndBind.setMovementMethod(ScrollingMovementMethod.getInstance());
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            RcyModel model = new RcyModel();
            model.type = 0;
            model.title = "这是第" + i + "个";
            mData.add(model);
        }
        mAdapter = new RcyAdapter(mData, this, rcy, tvCreateAndBind);
        rcy.setAdapter(mAdapter);
        rcy.setOnLayoutListener(new SRecyclerView.onLayoutListener() {
            @Override
            public void beforeLayout() {
                rcy.setAllCache();
            }

            @Override
            public void afterLayout() {
            }
        });
        rcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //RcyLog.logScrapCache(recyclerView);
                //RcyLog.logCache("onScroll时：", recyclerView);
                //RcyLog.logPool(recyclerView);
                RcyLog.loaAllCache(tvCacheView, rcy);
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initOperate() {
        TextView refresh = findViewById(R.id.refresh);
        TextView delete = findViewById(R.id.delete);
        refresh.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refresh:
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.delete:
                mData.remove(0);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }
}
