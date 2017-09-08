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

public class TypeTwoViewHolder  extends TypeAbstractViewHolder {
    public ImageView avatar;
    public TextView name;
    public TextView content;

    public TypeTwoViewHolder(View itemView) {
        super(itemView);
         avatar=(ImageView)itemView.findViewById(R.id.avatar);
         name=(TextView)itemView.findViewById(R.id.name);
         content=(TextView)  itemView.findViewById(R.id.content);
    }

    @Override
    public void bindHolder(DataModel model) {
        avatar.setBackgroundResource(model.avatarColor);
        name.setText(model.name);
        content.setText(model.content);
    }
}
