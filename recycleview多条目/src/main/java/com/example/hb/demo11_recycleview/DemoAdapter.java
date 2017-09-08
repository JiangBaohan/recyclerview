package com.example.hb.demo11_recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hb.demo11_recycleview.Holder.TypeOneViewHolder;
import com.example.hb.demo11_recycleview.Holder.TypeThreeViewHolder;
import com.example.hb.demo11_recycleview.Holder.TypeTwoViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2017/9/8
 * author:汉堡(Administrator)
 * function:多不局适配器
 * 1.创建一个类继承RecycleView的adapter
 * 2.定义RecycleView.viewholder以方便多不局
 * 3. 三种类型的item布局及viewholder
 * 4.使用getitemviewType方法
 * 5.在onCreateViewHolder和onBindViewHolder进行viewholder的创建,数据的判断加载
 */
public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DataModel> mlist = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;

    public DemoAdapter(Context context, List<DataModel> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mlist.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case DataModel.TYPE_ONE:
                TypeOneViewHolder holder = new TypeOneViewHolder(mLayoutInflater.inflate(R.layout.item_type_one, viewGroup, false));
                return holder;

            case DataModel.TYPE_TWO:
                TypeTwoViewHolder holder2 = new TypeTwoViewHolder(mLayoutInflater.inflate(R.layout.item_type_two, viewGroup, false));
                return holder2;

            case DataModel.TYPE_THREE:
                TypeThreeViewHolder holder3 = new TypeThreeViewHolder(mLayoutInflater.inflate(R.layout.item_type_three, viewGroup, false));
                return holder3;

            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*switch (getItemViewType(position)) {
            case 1:
                TypeOneViewHolder holderOne = (TypeOneViewHolder) holder;
                holderOne.name.setText(mlist.get(position).name);
                holderOne.avatar.setColorFilter(mlist.get(position).avatarColor);
                break;
            case 2:
                TypeTwoViewHolder holderTwo = (TypeTwoViewHolder) holder;
                holderTwo.name.setText(mlist.get(position).name);
                holderTwo.avatar.setColorFilter(mlist.get(position).avatarColor);
                holderTwo.content.setText(mlist.get(position).content);
                break;
            case 3:
                TypeThreeViewHolder holderTree = (TypeThreeViewHolder) holder;
                holderTree.name.setText(mlist.get(position).name);
                holderTree.avatar.setColorFilter(mlist.get(position).avatarColor);
                holderTree.content.setText(mlist.get(position).content);
                holderTree.contentimage.setColorFilter(mlist.get(position).contentColor);
                break;*/
//}
        ((TypeAbstractViewHolder)holder).bindHolder(mlist.get(position));
    }

    //根据不同的位置position返回不同的类型
    @Override
    public int getItemViewType(int position) {
        /*if (position%3==0){
            return DataModel.TYPE_ONE;
        }
        if (position%3==1){
            return DataModel.TYPE_TWO;
        }else
            return DataModel.TYPE_THREE;*/

        return mlist.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


}
