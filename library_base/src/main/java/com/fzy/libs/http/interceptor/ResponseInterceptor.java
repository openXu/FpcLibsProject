package com.fzy.libs.http.interceptor;

import com.fzy.libs.utils.FLog;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: openXu
 * Time: 2019/2/26 14:47
 * class: ResponseInterceptor
 * Description:
 */
public class ResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        FLog.i("okhttp拦截器拦截到请求:"+originalRequest.url());
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Request compressedRequest = originalRequest.newBuilder()
//                .header("Content-Encoding", "gzip")
//                .method(originalRequest.method(), gzip(originalRequest.body()))
//                .build();
//        Response response = chain.proceed(originalRequest);
//        FLog.i("okhttp拦截器拦截到响应:"+response.body().string());
        return chain.proceed(originalRequest);

    }
}
