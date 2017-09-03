package com.example.demo05_recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * data:2017/9/2
 * author:汉堡(Administrator)
 * function:
 * 1.继承RecyclerView.Adapter
 * 2.写viewholder
 * 3.定义范型<MyAdapter.ViewHolder>
 * 4.创建构造函数得到外界上下文和数据
 * 5.onCreateViewHolder创建布局的对象
 * 6.viewholder查找控件对象
 * 7.onBindViewHolder绑定数据
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<String> datas;
    private ViewHolder holder;

    //通过构造函数得到外界的上下文和数据
    public MyAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    //相当于getview方法,创建view对象和ViewHolder对象
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item, null);
        holder = new ViewHolder(inflate);
        return holder;
    }

    //相当于getview绑定数据不符的代码,数据和view的绑定
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String data = datas.get(i);
        holder.tv_title.setText(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            /*iv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //获取用户点击item的位置getLayoutPosintion
                  Toast.makeText(context,"用户点击第"+getPosition()+"个图片",Toast.LENGTH_SHORT).show();
                }
            });*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (monItemClickListener != null) {
                        monItemClickListener.onItemClick(view, datas.get(getPosition()));
                    }
                }
            });
        }
    }

    interface OnItemClickListener {
        /**
         * 抽象方法，当RecyclerView某个对象被点击是回调
         *
         * @param view 点击item对象
         * @param data 点击时的数据
         */
        void onItemClick(View view, String data);
    }

    //创建接口
    private OnItemClickListener monItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        monItemClickListener = onItemClickListener;
    }


    public void addData(int i, String name) {
        //datas.add(i,name);
        datas.add(0, name);
        notifyDataSetChanged();
       /* datas.add(i + "添加" + i);
        notifyItemInserted(i);
        //刷新指定区域 start end
        notifyItemRangeChanged(i, datas.size());*/

    }

    public void removeData(int i) {
        datas.remove(0);
        //notifyDataSetChanged();
        datas.remove(i);
        notifyItemRemoved(i);
        //局部刷新指定区域 参数对应头和尾
        notifyItemRangeChanged(i, datas.size());
    }
}
