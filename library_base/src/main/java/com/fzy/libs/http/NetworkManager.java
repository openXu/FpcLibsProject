package com.fzy.libs.http;

import android.app.Application;
import android.util.Log;


import com.fzy.libs.BuildConfig;
import com.fzy.libs.http.interceptor.ResponseInterceptor;
import com.fzy.libs.utils.FzyLog;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkManager {
    private String TAG = "NetworkManager";

    private static Application application;
    private Retrofit retrofit;
    private ApiService apiService;


    public static void init(Application context){
        application = context;
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
        // 初始化okhttp
        ResponseInterceptor interceptor = new ResponseInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void doGet(String url, Map<String, String> params, final HttpCallBack callBack){
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
                callBack.onError(t.getMessage());
            }
        });
    }

    public void doGet1(String url, Map<String, String> params, final HttpCallBack<OneSentence> callBack) {
        Observable<OneSentence> observable = apiService.rxGet(url, params);
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OneSentence>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        FzyLog.i("网络请求onSubscribe"+d);
                    }

                    @Override
                    public void onNext(OneSentence data) {
                        FzyLog.i("网络请求onNext"+data);
                        callBack.onSeccuce(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        FzyLog.i("网络请求onError"+e);
                    }

                    @Override
                    public void onComplete() {
                        FzyLog.i("网络请求onComplete");
                    }
                });
    }

}
