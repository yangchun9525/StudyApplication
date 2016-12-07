package com.example.administrator.on_off_button.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.on_off_button.R;
import com.example.administrator.on_off_button.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/28.
 */
public class RecycleActivity extends ActionBarActivity implements RecyclerViewAdapter.OnItemClickListener {
    private RecyclerView mRecycleView;
    private List<String> mData = new ArrayList<>();
    private RecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        mRecycleView = (RecyclerView) findViewById(R.id.recycleView);
        initData();
        mAdapter = new RecyclerViewAdapter(this,mData);
        mRecycleView.setAdapter(mAdapter);
        //设置为listview还是gridview,设置recycleView的布局管理
        LinearLayoutManager linearManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecycleView.setLayoutManager(linearManager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.add:
                mAdapter.addData(1);
                break;
            case R.id.delete:
                mAdapter.deleteData(1);
                break;
            case R.id.listview:
                LinearLayoutManager linearManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                mRecycleView.setLayoutManager(linearManager);
                break;
            case R.id.gridview:
                GridLayoutManager gridManager = new GridLayoutManager(this,3);
                mRecycleView.setLayoutManager(gridManager);
                break;
            case R.id.action_hor_gridview:
                StaggeredGridLayoutManager staManager = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL);
                mRecycleView.setLayoutManager(staManager);
                break;
            case R.id.action_staggered:
                startActivity(new Intent(RecycleActivity.this,StaggeredGridLayoutActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(RecycleActivity.this,position+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongItemClick(View view, int position) {
        Toast.makeText(RecycleActivity.this,position+"",Toast.LENGTH_SHORT).show();
    }
}
