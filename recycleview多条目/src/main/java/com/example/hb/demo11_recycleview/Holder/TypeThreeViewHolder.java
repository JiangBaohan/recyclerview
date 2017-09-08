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

public class TypeThreeViewHolder  extends TypeAbstractViewHolder {
    public ImageView avatar,contentimage;
    public TextView name,content;
    public TypeThreeViewHolder(View itemView) {
        super(itemView);
        avatar=(ImageView)itemView.findViewById(R.id.avatar);
        name=(TextView)itemView.findViewById(R.id.name);
        content=(TextView)itemView.findViewById(R.id.content);
        contentimage=(ImageView)itemView.findViewById(R.id.contentImage);
    }

    @Override
    public void bindHolder(DataModel model) {
        avatar.setBackgroundResource(model.avatarColor);
        contentimage.setBackgroundResource(model.contentColor);
        name.setText(model.name);
        content.setText(model.content);
    }
}
