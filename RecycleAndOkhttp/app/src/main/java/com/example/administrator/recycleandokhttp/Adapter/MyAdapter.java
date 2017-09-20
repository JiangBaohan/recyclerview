package com.example.administrator.recycleandokhttp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.recycleandokhttp.Bean.Mybean;
import com.example.administrator.recycleandokhttp.R;

import java.util.List;

/**
 * date:2017/9/20
 * author:humberger
 * function:
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //声明两个集合用于接受构造方法传来的参数在本地使用
    private List<Mybean.TopStoriesBean> list;

    //声明上下文引用，用于加载布局文件
    private Context context;

    //用构造方法传入需要的参数，
    public MyAdapter(Context context, List<Mybean.TopStoriesBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //返回MyViewHolder对象，通过构造方法传入加载布局文件得到的view对象
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tv.setText(list.get(position).title);
        Glide.with(context).load(list.get(position).image).into(holder.iv);

    }

    @Override
    public int getItemCount() {

        //返回数据源大小
        return list.size();
    }

    //自定义MyViewHolder类用于复用
    class MyViewHolder extends RecyclerView.ViewHolder {
        //声明imageview对象
        private ImageView iv;
        private  TextView tv;

        //构造方法中初始化imageview对象
        public MyViewHolder(final View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (monItemClickListener != null) {
                        monItemClickListener.onItemClick(itemView, getLayoutPosition());
                    }
                }
            });
            //长按事件借口回调
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(mOnItemLong != null){
                        mOnItemLong.onItemLongClick(itemView,getLayoutPosition());
                    }
                    return true;
                }
            });
        }
    }

    /**
     * 点击事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int postion);
    }

    //创建接口
    private OnItemClickListener monItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        monItemClickListener = onItemClickListener;
    }

    /**
     * 长按点击事件
     */
    public interface OnRecyclerItemLongListener{
        void onItemLongClick(View view,int position);
    }
    private OnRecyclerItemLongListener mOnItemLong;
    public void setOnItemLongClickListener(OnRecyclerItemLongListener listener){
        this.mOnItemLong =  listener;
    }

}




