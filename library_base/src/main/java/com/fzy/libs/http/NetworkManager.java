package com.fzy.libs.http;

import android.content.Context;
import android.util.Log;


import com.fzy.libs.BuildConfig;
import com.fzy.libs.base.BaseApplication;
import com.fzy.libs.http.converter.FzyGsonConverterFactory;
import com.fzy.libs.http.interceptor.LoggerInterceptor;
import com.fzy.libs.http.rx.BaseOberver;
import com.fzy.libs.http.rx.NetErrorObserver;
import com.fzy.libs.http.rx.ParseDataFuncation;
import com.fzy.libs.utils.FLog;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public class NetworkManager {
    private String TAG = "NetworkManager";

    private Retrofit retrofit;
    private ApiService apiService;

    private static Context context;
    static {
        context = BaseApplication.getApplication().getApplicationContext();
    }

    private static class SingletonHolder{
        private static final NetworkManager INSTANCE = new NetworkManager();
    }
   /* public static NetworkManager getInstance(Context context){
        if(SingletonHolder.INSTANCE.context == null)
            SingletonHolder.INSTANCE.context = context.getApplicationContext();
        return SingletonHolder.INSTANCE;
    }*/
    public static NetworkManager getInstance(){
       return SingletonHolder.INSTANCE;
   }
    private NetworkManager(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor interceptor1 = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String text = URLDecoder.decode(message, "utf-8");
                    Log.e("OKHttp-----", text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e("OKHttp-----", message);
                }
            }
        });
        // 初始化okhttp
        OkHttpClient client = new OkHttpClient.Builder()
//                .retryOnConnectionFailure(true)//默认重试一次，若需要重试N次，则要实现拦截器。
        .retryOnConnectionFailure(false)
                .dns(new TimeOutDns(3000, TimeUnit.MILLISECONDS))  //DNS解析超时，如果不设置可能回出现在网络不可用的情况下，DNS解析时间太长
                .connectTimeout(2000, TimeUnit.MILLISECONDS)       //IP连接超时
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
//                .addInterceptor(new ResponseInterceptor())
                .addInterceptor(new LoggerInterceptor())
                .addInterceptor(new HttpLoggingInterceptor())
//                .addInterceptor(new RetryInterceptor(2))  //重试
                .build();

        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FzyGsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void doGet(String url, Map<String, String> params, final HttpCallBack callBack){
        //3. 对 发送请求 进行封装
        Call<ResponseBody> call = apiService.doGet(url, params);
        //4. 发送网络请求(异步)
        final Type type = callBack.getClass().getGenericSuperclass();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Log.d(TAG, "Http NetResponse：" + result);
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
                FLog.e("Request onFailure：" + t.getMessage());
                callBack.onError(t.getMessage());
            }
        });
    }

    public <T> void doGetByRx(String url, Map<String, String> params,String jsonStr, final BaseOberver<T> observer) {
        apiService.rxGet(url, params)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(0)//请求失败重试次数
//                .map(new ParseDataFuncation(observer))
                .map(new ParseDataFuncation(observer,jsonStr))
                .onErrorResumeNext(new NetErrorObserver<T>())
                .subscribe(observer);
    }
}
