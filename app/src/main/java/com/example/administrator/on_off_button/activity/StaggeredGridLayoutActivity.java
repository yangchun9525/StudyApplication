package com.example.administrator.on_off_button.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.administrator.on_off_button.R;
import com.example.administrator.on_off_button.adapter.StaggerGridViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */
public class StaggeredGridLayoutActivity extends ActionBarActivity {
    private RecyclerView mRecycleView;
    private List<String> mData = new ArrayList<>();
    private StaggerGridViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        mRecycleView = (RecyclerView) findViewById(R.id.recycleView);
        initData();
        mAdapter = new StaggerGridViewAdapter(this,mData);
        mRecycleView.setAdapter(mAdapter);

        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        //设置recycleView的分割线
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }

    private void initData() {
        mData.add("A");
        mData.add("B");
        mData.add("C");
        mData.add("D");
        mData.add("E");
        mData.add("F");
        mData.add("G");
        mData.add("H");
        mData.add("I");
        mData.add("J");
        mData.add("K");
        mData.add("L");
        mData.add("M");
        mData.add("N");
        mData.add("O");
        mData.add("P");
        mData.add("Q");
        mData.add("R");
        mData.add("S");
        mData.add("T");
        mData.add("U");
        mData.add("V");
        mData.add("W");
        mData.add("X");
        mData.add("Y");
        mData.add("Z");
    }
}
