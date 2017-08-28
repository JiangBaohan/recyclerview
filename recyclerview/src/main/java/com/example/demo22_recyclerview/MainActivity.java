package com.example.demo22_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 有list,grid,瀑布流三种效果,就是管理者不同,所以参数略有不同,
 * 适配器方法都相同
 * 其中grid是继承的LinearLayoutManager
 * 其他两个是LayoutManager
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        loadListData(false, true);
    }

    //list效果
    private void loadListData(Boolean inversion, Boolean orientation) {
        List<Bean> list = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            Bean bean = new Bean();
            bean.image = R.drawable.a4;
            bean.name = "balabalaxiaomoxian" + i;
            list.add(bean);
        }
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, list);
        rv.setAdapter(adapter);
        //RecyclerView显示的样式,默认list格式显示创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置条目的排列顺序true反向排序,flase为正常显示(默认)
        manager.setReverseLayout(inversion);
        //设置方向,默认是垂直的
        manager.setOrientation(orientation ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);//水平
        //设置布局管理器
        rv.setLayoutManager(manager);
    }

    /**
     * 表格的效果图
     *
     * @param inversion
     * @param orientation
     */
    private void loadGridData(Boolean inversion, Boolean orientation) {
        List<Bean> list = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            Bean bean = new Bean();
            bean.image = R.drawable.a4;
            bean.name = "balabalaxiaomoxian" + i;
            list.add(bean);
        }
        //***************与list效果的不同,管理者不同**************
        RecyclerViewGridAdapter adapter = new RecyclerViewGridAdapter(this, list);
        rv.setAdapter(adapter);
        //参数 上下文和显示的列数
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        //设置条目的排列顺序true反向排序,flase为正常显示(默认)
        manager.setReverseLayout(inversion);
        //设置方向,默认是垂直的
        manager.setOrientation(orientation ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);//水平
        //设置布局管理器
        rv.setLayoutManager(manager);
    }

    /**
     * 瀑布流
     */
    private void loadStaggeredData(Boolean inversion, Boolean orientation) {
        List<Bean> list = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            Bean bean = new Bean();
            bean.image = R.drawable.a4;
            bean.name = "balabalaxiaomoxian" + i;
            list.add(bean);
        }

        RecyclerViewStaggeredAdapter adapter = new RecyclerViewStaggeredAdapter(this, list);
        rv.setAdapter(adapter);
        //参数 显示的列数和方向
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, orientation ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
        //设置条目的排列顺序true反向排序,flase为正常显示(默认)
        manager.setReverseLayout(inversion);
        //设置布局管理器
        rv.setLayoutManager(manager);
    }

    public void btn(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //都是默认的,正序,垂直
                loadListData(false, true);
                break;
            case R.id.btn2:
                //反序,水平
                loadListData(true, false);
                break;
            case R.id.btn3:
                loadGridData(false, true);
                break;
            case R.id.btn4:
                loadGridData(true, false);
                break;
            case R.id.btn5:
                loadStaggeredData(false, true);
                break;
            case R.id.btn6:
                loadStaggeredData(true, false);
                break;
            default:
                break;
        }
    }
}
