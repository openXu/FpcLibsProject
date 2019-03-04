package com.fzy.libs.http;

import com.fzy.libs.http.base.FzyResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {


    /*
     * etrofit不支持二次泛型 https://github.com/square/retrofit/issues/2012
     * 报错Method return type must not include a type variable or wildcard: retrofit2.Call<com.fpc.net.NetResponse<T>>
     */
//    @GET
//    Call<NetResponse<T>> doGet(@Url String url, @QueryMap Map<String, String> map);


    /*
     * http://open.iciba.com/dsapi/?date=2019-02-15
     * @Query 用于@GET方法的查询参数 key-value
     */
    @GET
    Call<ResponseBody> doGet(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<ResponseBody> rxGet(@Url String url, @QueryMap Map<String, String> map);


}
