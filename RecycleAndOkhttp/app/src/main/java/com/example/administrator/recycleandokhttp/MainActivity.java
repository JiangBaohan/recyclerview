package com.example.administrator.recycleandokhttp;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.recycleandokhttp.Adapter.MyAdapter;
import com.example.administrator.recycleandokhttp.Bean.Mybean;
import com.example.administrator.recycleandokhttp.Utils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.recycleandokhttp.R.id.recyclerview;

/**
 * recyclerview+okhttp+点击事件和长按点击事件
 * 上拉刷新下拉加载还没弄
 */
public class MainActivity extends AppCompatActivity {
    List<Mybean.TopStoriesBean> top_stories = new ArrayList<>();
    //声明recyclerview引用
    private RecyclerView mRecyclerView;
    //声明自定义请求类
    private MyAdapter adapter;
    private String url = "http://news-at.zhihu.com/api/4/news/latest";
    private SwipeRefreshLayout swipeRefreshLayout;
    int page=1;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        initData();



    }

    private void initData() {
        //上拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                page++;
                startTask();//开启网络下载数据的方法
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //下拉加载
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == top_stories.size() - 1) {
                    page++;
                    startTask();
                   //adapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void startTask() {

        //通过类名直接调用静态方法获取单例对象再调用getBeanOfOK()方法传入参数通过接口回调获取数据
        OkHttpUtils.getInstance().getBeanOfOk(this, url, Mybean.class,
                new OkHttpUtils.CallBack<Mybean>() {
                    @Override
                    public void getData(Mybean mybean) {

                        Log.i("===", "getData: " + mybean.toString());
                        if (mybean != null) {

                            //如果不为空用本地list接收
                            top_stories.addAll(mybean.top_stories);
                            //数据一旦回调成功初始化数据源和适配器
                            initAdapter();
                        }
                    }
                });

    }

    private void initAdapter() {


        //创建自定义适配器对象
        adapter = new MyAdapter(this, top_stories);

        //设置recyclerview适配器
        mRecyclerView.setAdapter(adapter);


        //刷新适配器
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                String title = top_stories.get(postion).title;
                Toast.makeText(MainActivity.this,title,Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(new MyAdapter.OnRecyclerItemLongListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
