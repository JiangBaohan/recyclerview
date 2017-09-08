package com.example.hb.demo11_recycleview.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hb.demo11_recycleview.DataModel;
import com.example.hb.demo11_recycleview.R;
import com.example.hb.demo11_recycleview.TypeAbstractViewHolder;

/**
 * data:2017/9/8
 * author:汉堡(Administrator)
 * function:
 */

public class TypeOneViewHolder extends TypeAbstractViewHolder {
    public ImageView avatar;
    public TextView name;
    public TypeOneViewHolder(View itemView) {
        super(itemView);
         avatar=(ImageView)itemView.findViewById(R.id.avatar);
         name=(TextView) itemView.findViewById(R.id.name);
    }
//viewholder数据和外面数据绑定起来
    @Override
    public void bindHolder(DataModel model) {
        avatar.setBackgroundResource(model.avatarColor);
        name.setText(model.name);
    }
}
