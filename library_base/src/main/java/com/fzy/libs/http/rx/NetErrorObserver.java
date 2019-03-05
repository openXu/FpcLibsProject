package com.fzy.libs.http.rx;

import com.fzy.libs.http.error.NetError;
import com.fzy.libs.http.error.NetErrorHandle;
import com.fzy.libs.utils.FLog;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Author: openXu
 * Time: 2019/3/4 16:19
 * class: NetErrorObserver
 * Description: 错误处理Function
 */
public class NetErrorObserver<T> implements Function<Throwable, ObservableSource<? extends T>> {
    @Override
    public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
        FLog.e("请求错误："+throwable);
        NetError error = NetErrorHandle.handleError(throwable);
        return Observable.error(error);
    }
}
