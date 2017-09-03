package com.example.demo05_recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_add;
    private Button btn_delete;
    private Button btn_list;
    private Button btn_grid;
    private Button btn_flow;
    private RecyclerView recyclerview;

    private ArrayList<String> datas;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        adapter = new MyAdapter(this, datas);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(MainActivity.this, "点击了"+data, Toast.LENGTH_SHORT).show();
            }
        });
        //添加分割线
        recyclerview.addItemDecoration(new DividerListItemDecoration(this,DividerListItemDecoration.VERTICAL_LIST));
        //设置默认动画,可以自定义
        recyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    private void initView() {
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_grid = (Button) findViewById(R.id.btn_grid);
        btn_flow = (Button) findViewById(R.id.btn_flow);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        //设置点击事件
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_flow.setOnClickListener(this);
    }

    private void initData() {
        datas = new ArrayList<>();
        //准备数据集合
        for (int i = 0; i < 100; i++) {
            datas.add("Content_" + i);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add://D.添加数据
                adapter.addData(0, "+1");
               // datas.add("+1");
               // adapter.notifyItemChanged(0);
                //定位的指定的位置
                //recyclerview.scrollToPosition(0);
                //adapter.addData(0);
                break;

            case R.id.btn_delete://D.删除数据
            adapter.removeData(0);
                break;

            case R.id.btn_list://设置List类型效果
                recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

                break;

            case R.id.btn_grid://设置Grid类型效果
                /*GridLayoutManager manager= new GridLayoutManager(this,3);
                //设置条目的排列顺序true反向排序,flase为正常显示(默认)
                manager.setReverseLayout(false);*/
                recyclerview.setLayoutManager(new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false));
                break;

            case R.id.btn_flow://设置瀑布流类型效果
                //参数 显示的列数和方向
               /* StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                //设置条目的排列顺序true反向排序,flase为正常显示(默认)
                manager2.setReverseLayout(false);*/
                //设置布局管理器
              recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL));
                break;
        }
    }
}
