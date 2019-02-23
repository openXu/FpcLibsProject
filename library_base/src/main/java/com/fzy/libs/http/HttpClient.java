package com.fzy.libs.http;

import android.content.Context;
import android.util.Log;


import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpClient{
    private String TAG = "HttpUtils";

    private Context context;

    private static class SingletonHolder{
        private static final HttpClient INSTANCE = new HttpClient();
    }
   /* public static HttpClient getInstance(Context context){
        if(SingletonHolder.INSTANCE.context == null)
            SingletonHolder.INSTANCE.context = context.getApplicationContext();
        return SingletonHolder.INSTANCE;
    }*/
   public static HttpClient getInstance(){
       return SingletonHolder.INSTANCE;
   }
    public void init(Context context){
       this.context = context;
    }
    private HttpClient(){
    }

    private Retrofit retrofit;


    public void doGet(String url, Map<String, String> params, final HttpCallBack callBack){
        //1. 创建 Retrofit 实例  http://open.iciba.com/dsapi/?date=2019-02-15
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://open.iciba.com/") // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台
                .build();
        //2. 创建 网络请求接口 的实例
        ApiService apiService = retrofit.create(ApiService.class);
        //3. 对 发送请求 进行封装
        Call<ResponseBody> call = apiService.doGet(url, params);
        //4. 发送网络请求(异步)
        final Type type = callBack.getClass().getGenericSuperclass();
        Log.e(TAG, "-----------------"+type);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.d(TAG, "Request onResponse：" + call.request());
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Log.d(TAG, "Http Response：" + result);
                        callBack.onSeccuce(result);
                        // 根据当前类获取泛型的Type
//                        Type[] tClass =  ((ParameterizedType)type).getActualTypeArguments();
//                        callBack.onSeccuce(new Gson().fromJson(result, tClass[0]));
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                callBack.onError(response.message());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }


}
