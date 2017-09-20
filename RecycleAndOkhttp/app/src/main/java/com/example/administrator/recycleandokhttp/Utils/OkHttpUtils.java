package com.example.administrator.recycleandokhttp.Utils;

import android.app.Activity;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2017/9/20
 * author:humberger
 * function:
 */

//OkHttpUtils工具类，用于 用OkHttp框架获取网络数据并通过添加范型的静态方法返回不同
//bean对象的数据
public class OkHttpUtils {

    //声明OkHttpClient引用
    private OkHttpClient client;

    //私有化构造方法
    private OkHttpUtils(){

        //创建client对象只在创建OkHttpUitls对象时创建一次
        client = new OkHttpClient();

    }

    //声明静态OkHttpUtils引用
    private static OkHttpUtils utils;

    //单例设计模式让外部始终获得的是一个OkHttpUtils对象
    public static OkHttpUtils getInstance() {

        //双重判断加上同步锁可以解决线程安全问题
        if(utils==null){
            synchronized (OkHttpUtils.class){
                if(utils==null){

                    //一旦判断没有创建过此类的对象就创建一次并返回
                    utils = new OkHttpUtils();

                    return utils;
                }
            }
        }
        return utils;
    }

    //定义一个callback接口并定义范型（适用于任意类的返回对象）用于接口回调
    public interface CallBack<T>{

        //定义回调方法
        void getData(T t);
    }

    //创建万能的数据请求类，形参为四个activity对象，网址url,和得到数据的bean类为范型的class对象，实现接口的类对象
    public <T extends Object>void getBeanOfOk(final Activity activity, String url, final Class<T> clazz, final CallBack<T> callback){

        //创建request请求对象，设置其方式get,网址url
        Request request = new Request.Builder().get().url(url).build();

        //通过client的newCall方法传入request，并调用enqueue求求数据
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //通过返回的response对象的body()和string()方法得到获取的json字符串
                String json = response.body().string();

                //通过Gson解析得到解析后任意类对象
                final T t  = new Gson().fromJson(json,clazz);

                //由于子线程中不能更新UI所以我们用activity对象调用方法回到主线程
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(t!=null){
                            //并用过接口回调传回数据
                            callback.getData(t);
                        }
                    }
                });
            }
        });
    }
}
