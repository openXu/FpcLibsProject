package com.fzy.libs.http;

import android.content.Context;
import android.util.Log;


import com.fzy.libs.BuildConfig;
import com.fzy.libs.base.BaseApplication;
import com.fzy.libs.http.base.BaseOberver;
import com.fzy.libs.http.converter.FzyGsonConverterFactory;
import com.fzy.libs.http.error.HttpError;
import com.fzy.libs.http.error.HttpErrorHandle;
import com.fzy.libs.http.error.HttpErrorObserver;
import com.fzy.libs.http.interceptor.LoggerInterceptor;
import com.fzy.libs.http.interceptor.RetryInterceptor;
import com.fzy.libs.utils.FLog;
import com.fzy.libs.utils.toasty.FToast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
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
                .addInterceptor(new RetryInterceptor(2))  //重试
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

    public void doGet1(String url, Map<String, String> params, final HttpCallBack<OneSentence> callBack) {
        Observable<ResponseBody> observable = apiService.rxGet(url, params);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //处理Http相关错误
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends ResponseBody>>() {
                    @Override
                    public ObservableSource<? extends ResponseBody> apply(Throwable throwable) throws Exception {
                        HttpError error = HttpErrorHandle.handleError(throwable);
                        FLog.e("Http请求错误："+error);
                        //给用户错误提示
//        FToast.error(error.getToastMsg());
                        return Observable.error(error);
                    }
                })
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        FLog.i("onSubscribe开始订阅了");
                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        FLog.w("onNext开始发射了:"+responseBody);
                    }
                    @Override
                    public void onError(Throwable e) {
                        FLog.e("发射错误了onError "+e);

                    }
                    @Override
                    public void onComplete() {
                        FLog.i("发射完成了onComplete");
                    }
                });
    }
    public <T> void doGet2(String url, Map<String, String> params, final BaseOberver<T> observer) {
        Observable<ResponseBody> observable = apiService.rxGet(url, params);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //处理Http相关错误
           /*     .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends ResponseBody>>() {
                    @Override
                    public ObservableSource<? extends ResponseBody> apply(Throwable throwable) throws Exception {
                        HttpError error = HttpErrorHandle.handleError(throwable);
                        FLog.e("Http请求错误："+error);
                        //给用户错误提示
                        FToast.error(error.getToastMsg());
                        return Observable.empty();  //创建一个不发射任何数据但是正常终止的Observable；
                    }
                })*/
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(ResponseBody responseBody) throws Exception {
                        FLog.i("map接受到数据:"+responseBody);
                        Class<T> tClass = (Class<T>) ((ParameterizedType) observer.getClass().getGenericSuperclass()).getActualTypeArguments()[0]; // 根据当前类获取泛型的Type
                        return new Gson().fromJson("{\"userName\":\"xxxxx\", \"password\":\"sssss\"c}", tClass);
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
                        HttpError error = HttpErrorHandle.handleError(throwable);
                        FLog.e("Http请求错误："+error);
                        //给用户错误提示
                        FToast.error(error.getToastMsg());
                        return Observable.empty();  //创建一个不发射任何数据但是正常终止的Observable；
                    }
                })
                .subscribe(observer);
    }
}
