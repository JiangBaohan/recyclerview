package com.example.hb.mooth9_examdemo02;

import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hb.mooth9_examdemo02.Bean.Data;
import com.example.hb.mooth9_examdemo02.adapter.RecyclerAdapter;
import com.example.hb.mooth9_examdemo02.utils.ItemDecration;
import com.example.hb.mooth9_examdemo02.utils.OkHttpUtils;
import com.example.hb.mooth9_examdemo02.utils.OnItemonclicklinear;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Data.DataBean> list = new ArrayList<>();
    private int page = 1;
    private String url = "http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=20&gender=2&ts=1871746850&page=";
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    //初始化数据
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        //线性布局管理及设置
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new ItemDecration(this));
        initData();

    }

    private void loadData() {

        //通过类名直接调用静态方法获取单例对象再调用getBeanOfOK()方法传入参数通过接口回调获取数据
        OkHttpUtils.getInstance().getBeanOfOk(this, url + page, Data.class,
                new OkHttpUtils.CallBack<Data>() {
                    @Override
                    public void getData(Data testBean) {

                        Log.i("===", "getData: " + testBean.toString());
                        if (testBean != null) {
                            //如果不为空用本地list接收
                            list.addAll(testBean.getData());
                            //数据一旦回调成功初始化数据源和适配器
                            initAdapter();
                        }
                    }
                });
    }

    //设置适配器
    public void initAdapter() {
        adapter = new RecyclerAdapter(list, this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemonclicklinear(new OnItemonclicklinear() {
            @Override
            public void onClickListener(View v, int position) {
                Data.DataBean bean = list.get(position);
                Toast.makeText(MainActivity.this, bean.getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(new RecyclerAdapter.OnRecyclerItemLongListener() {
            @Override
            public void onItemLongClick(View view, int position) {
               // Toast.makeText(MainActivity.this,"changanlelalalalalala",Toast.LENGTH_SHORT).show();
            dialog1_1(position);
            }
        });
    }

    private void initData() {
        //上拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page++;
                loadData();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //下拉加载
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == list.size() - 1) {
                    page++;
                    loadData();
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    private void dialog1_1(final int position){
        //先new出一个监听器，设置好监听
        DialogInterface.OnClickListener dialogOnclicListener=new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case Dialog.BUTTON_POSITIVE:
                        Toast.makeText(MainActivity.this, "确认" + position, Toast.LENGTH_SHORT).show();
                        adapter.removeData(position);
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        Toast.makeText(MainActivity.this, "取消" + position, Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        //dialog参数设置
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        //builder.setTitle("提示"); //设置标题
        builder.setMessage("是否确认删除?"); //设置内容
       // builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确认",dialogOnclicListener);
        builder.setNegativeButton("取消", dialogOnclicListener);
        //builder.setNeutralButton("忽略", dialogOnclicListener);
        builder.create().show();
    }
}
