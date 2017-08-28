package com.example.demo22_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * data:2017/8/28
 * author:汉堡(Administrator)
 * function:
 */
public class RecyclerViewGridAdapter extends RecyclerView.Adapter<RecyclerViewGridAdapter.ViewHolder>{
    private Context context;
    private List<Bean> list;
    private ViewHolder holder;
    public RecyclerViewGridAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = View.inflate(context, R.layout.item_grid, null);
        holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Bean bean = list.get(i);
        holder.setData(bean);//给holder添加数据
    }

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
            imageView = (ImageView) itemView.findViewById(R.id.grid_iv);
            textView = (TextView) itemView.findViewById(R.id.grid_tv);
        }

        public void setData(Bean data) {
            this.data = data;
            imageView.setImageResource(data.image);
            textView.setText(data.name);
        }
    }
}
