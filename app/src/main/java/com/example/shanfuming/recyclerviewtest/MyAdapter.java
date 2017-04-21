package com.example.shanfuming.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shanfuming on 2017/4/21.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private ArrayList<String> mList;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    private Context context;

    public MyAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.mList = list;
        inflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener {
        void onClick1(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }

    /**
     * 返回我们自定义继承了ViewHolder 的MyViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recyclerview,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    /**
     * 将view 与数据进行绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.textView.setText(mList.get(position));
        //添加点击事件
        if (mOnItemClickListener != null){

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick1(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }

    }

    /**
     * 返回数据的总个数
     * @return
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_name)
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    /**
     * 动态增加或删除条目
     * @param position
     */
    public void addData(int position) {
        mList.add(position, "add One");
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}