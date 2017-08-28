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
public class RecyclerViewStaggeredAdapter extends RecyclerView.Adapter<RecyclerViewStaggeredAdapter.StaggerViewHolder>{
private Context context;
    private List<Bean> list;
    private StaggerViewHolder holder;

    public RecyclerViewStaggeredAdapter(Context context, List<Bean> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public StaggerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = View.inflate(context, R.layout.item_stagger, null);
        holder = new StaggerViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(StaggerViewHolder staggerViewHolder, int i) {
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

    public class StaggerViewHolder extends RecyclerView.ViewHolder {
        private Bean data;
        private final ImageView imageView;
        private final TextView textView;
        //找控件,itemview是View.inflate得到的
        public StaggerViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.Stagger_iv);
            textView = (TextView) itemView.findViewById(R.id.Stagger_tv);
        }


        public void setData(Bean data) {
            this.data = data;
            imageView.setImageResource(data.image);
            textView.setText(data.name);
        }
    }
}
