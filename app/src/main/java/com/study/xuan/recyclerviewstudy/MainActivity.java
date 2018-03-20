package com.study.xuan.recyclerviewstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SRecyclerView rcy;
    private RcyAdapter mAdapter;
    private ArrayList<RcyModel> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOperate();
        rcy = findViewById(R.id.rcy);
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            RcyModel model = new RcyModel();
            model.type = 0;
            model.title = "这是第" + i + "个";
            mData.add(model);
        }
        mAdapter = new RcyAdapter(mData, this, rcy);
        rcy.setAdapter(mAdapter);
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.setOnLayoutListener(new SRecyclerView.onLayoutListener() {
            @Override
            public void beforeLayout() {
                //RcyLog.logScrapCache(rcy);
            }

            @Override
            public void afterLayout() {
                //RcyLog.logScrapCache(rcy);
            }
        });
        rcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //RcyLog.logScrapCache(recyclerView);
                RcyLog.logCache("onScroll时：", recyclerView);
                RcyLog.logPool(recyclerView);
            }
        });
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
