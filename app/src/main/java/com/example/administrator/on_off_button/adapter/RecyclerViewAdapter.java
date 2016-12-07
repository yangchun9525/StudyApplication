package com.example.administrator.on_off_button.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.on_off_button.R;

import java.util.List;

/**
 * Created by Administrator on 2016/2/28.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Context mContext;
    private List<String> data;
    private LayoutInflater mInflater;
    public RecyclerViewAdapter(Context mContext,List<String> data){
        this.mContext = mContext;
        this.data = data;
        mInflater = LayoutInflater.from(mContext);
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onLongItemClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_recycle_view,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder,final int i) {
        myViewHolder.tv.setText(data.get(i));
        if(mOnItemClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPostion = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(myViewHolder.itemView, layoutPostion);
                }
            });

            myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //获取在屏幕上的实际位置
                    int layoutPostion = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onLongItemClick(myViewHolder.itemView, layoutPostion);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addData(int position){
        data.add(position,"data");
        notifyItemInserted(position);
    }

    public void deleteData(int position){
        data.remove(position);
//        notifyItemInserted(position);
        notifyItemRemoved(position);
        //notifyDataSetChanged无动画效果
//        notifyDataSetChanged();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    TextView tv;
    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv);
    }
}

