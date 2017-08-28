package com.example.demo22_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * data:2017/8/23
 * author:汉堡(Administrator)
 * function:RecyclerViewAdapter适配器要写泛型,一般是类名.viewholder
 * 会实现三个方法,自动创建viewholder 需要继承RecyclerView.ViewHolder
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Bean> list;
    private ViewHolder holder;

    public RecyclerViewAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    //创建viewholder
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = View.inflate(context, R.layout.item, null);
        holder = new ViewHolder(itemView);
        return holder;
    }

    /**
     * 当viewhonlder和数据绑定时回调
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int i) {
        Bean bean = list.get(i);
        holder.setData(bean);//给holder添加数据

    }

    //集合条目数,有几条显示几条,需要先做个集合非空并且大于0的判断
    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Bean data;
        private final ImageView imageView;
        private final TextView textView;
        //找控件,itemview是View.inflate得到的
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.text);

        }
        //设置数据(修改)
        public void setData(Bean data) {
            this.data = data;
            imageView.setImageResource(data.image);
            textView.setText(data.name);
        }
    }
}
