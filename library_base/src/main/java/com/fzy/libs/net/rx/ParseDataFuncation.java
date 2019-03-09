package com.fzy.libs.net.rx;

import com.fzy.libs.net.data.FzyParameterizedTypeImpl;
import com.fzy.libs.net.data.FzyResponse;
import com.fzy.libs.net.error.BusinessError;
import com.fzy.libs.utils.FLog;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * Author: openXu
 * Time: 2019/3/5 16:40
 * class: ParseDataFuncation
 * Description: 用于解析服务器返回数据的Funcation
 */
public class ParseDataFuncation<T> implements Function<ResponseBody, T> {

    private Type type;
    public ParseDataFuncation(BaseOberver<T> observer){
        try{
            //通过BaseOberver<T>获取T的class
            Class<T> tClass = (Class<T>) ((ParameterizedType) observer.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            type = new FzyParameterizedTypeImpl(FzyResponse.class, new Class[]{tClass});
        }catch (Exception e){
            FLog.e(e.getMessage());
            //获取List<T> 的 泛型class
            Class c = observer.getClass();
            Type t = c.getGenericSuperclass();   //BaseOberver<java.util.List<com.fzy.mbase.bean.Anim>>
            Class c1 = t.getClass();             //class libcore.reflect.ParameterizedTypeImpl
            Type listType = ((ParameterizedType)t).getOwnerType();   //null
            listType = ((ParameterizedType)t).getRawType();   //class com.fzy.libs.http.rx.BaseOberver
            Type[] types  = ((ParameterizedType)t).getActualTypeArguments();   //[Ljava.lang.reflect.Type;@df2f9d0 也就是 java.util.List<com.fzy.mbase.bean.Anim>
            type = new FzyParameterizedTypeImpl(FzyResponse.class, types);
        }
    }
    /**测试*/
    String jsonStr;
    public ParseDataFuncation(BaseOberver<T> observer, String jsonStr){
        this(observer);
        this.jsonStr = jsonStr;
    }

    @Override
    public T apply(ResponseBody responseBody) throws Exception {
        FzyResponse<T> response = new Gson().fromJson(jsonStr, type);
        if(response.isSuccess()){
            return response.getData();
        }else{
            throw new BusinessError(response.getMassage());
        }
    }
}
