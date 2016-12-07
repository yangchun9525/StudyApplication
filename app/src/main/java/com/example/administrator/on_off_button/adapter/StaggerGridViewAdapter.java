package com.example.administrator.on_off_button.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.on_off_button.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/28.
 */
public class StaggerGridViewAdapter extends RecyclerView.Adapter<StaggerGridViewAdapter.StaggerGridViewHolder>{
    private Context mContext;
    private List<String> data;
    private LayoutInflater mInflater;
    private List<Integer> mHeights;
    public StaggerGridViewAdapter(Context mContext, List<String> data){
        this.mContext = mContext;
        this.data = data;
        mInflater = LayoutInflater.from(mContext);
        mHeights = new ArrayList<>();
        for (int i = 0;i< data.size();i++){
            mHeights.add((int)(100+Math.random()* 300));
        }
    }

    @Override
    public StaggerGridViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_recycle_view,viewGroup,false);
        StaggerGridViewHolder viewHolder = new StaggerGridViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StaggerGridViewHolder myViewHolder, int i) {
        ViewGroup.LayoutParams lp = myViewHolder.itemView.getLayoutParams();
        lp.height = mHeights.get(i);
        myViewHolder.itemView.setLayoutParams(lp);
        myViewHolder.tv.setText(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class StaggerGridViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public StaggerGridViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
