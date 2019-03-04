package com.fzy.libs.http.error;

import com.fzy.libs.utils.FLog;
import com.fzy.libs.utils.toasty.FToast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * Author: openXu
 * Time: 2019/3/4 16:19
 * class: HttpErrorObserver
 * Description:
 */
public class ApiErrorObserver<T> implements Function<ResponseBody, T> {
    @Override
    public T apply(ResponseBody responseBody) throws Exception {
        FLog.i("map接受到数据:"+responseBody);
        Gson gson = new Gson();

        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; // 根据当前类获取泛型的Type
//                        Type ty = new ParameterizedTypeImpl(BaseResponse.class, new Class[]{tClass}); // 传泛型的Type和我们想要的外层类的Type来组装我们想要的类型
        //com.google.gson.internal.LinkedTreeMap cannot be cast to com.fzy.mbase.bean.User
        return gson.fromJson("{\"userName\":\"xxxxx\", \"password\":\"sssss\"}", tClass);
    }
}
