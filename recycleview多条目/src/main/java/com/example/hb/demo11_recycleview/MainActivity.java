package com.example.hb.demo11_recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现RecycleView多布局效果
 * 1.搭建环境
 * 2.初始化数据
 * 3.创建适配器
 * 4.设置适配器,
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DataModel> list;
    int colors[] = {android.R.color.holo_blue_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        initData();//初始化数据
        DemoAdapter adapter = new DemoAdapter(this, list);
        recyclerView.setAdapter(adapter);
      //  recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
       //列表和表格效果混排,无论一行两列还是一行一列都是GridLayoutManager效果
        //做一个监听器,让其显示的列数不同
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                int type = recyclerView.getAdapter().getItemViewType(i);
                //根据return类型,决定gridLayoutManager,一行占用几列
                if (type==DataModel.TYPE_THREE){
                    return gridLayoutManager.getSpanCount();
                }else {
                    return 1;
                }
            }
        });
    }

    /**
     * Type:分类型
     * String name
     * String content
     * color
     */
    private void initData() {
//创建集合存放bean类
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            //int type = (int) (Math.random() * 3 + 1);


            /**
             * 列表和表格的混排
             */
            int type;
            if (i < 5 || (i > 15 && i < 20) ){
                type = 1;
            }else if (i < 10 || i > 20) {
                type = 2;
            } else {
                type = 3;
            }
            //创建bean类
            DataModel data = new DataModel();
            data.avatarColor = colors[type - 1];
            data.type = type;
            data.name = "Name" + i;
            data.content = "Content" + i;
            data.contentColor = colors[(type + 1) % 2];
            list.add(data);
        }
    }
}
