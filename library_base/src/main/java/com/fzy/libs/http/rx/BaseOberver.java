package com.fzy.libs.http.rx;

import com.fzy.libs.http.error.NetError;
import com.fzy.libs.utils.FLog;
import com.fzy.libs.utils.toasty.FToast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author: openXu
 * Time: 2019/3/4 17:18
 * class: BaseOberver
 * Description:
 */
public abstract class BaseOberver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        onStart();
    }
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }
    @Override
    public void onError(Throwable e) {
        FLog.e(((NetError)e).getMassage());   //具体错误信息，通过log打印方便调试
        onFail(((NetError)e).getUserMsg());   //给用户提示的错误信息
        onFinish();
    }
    @Override
    public void onComplete() {
        onFinish();
    }

    public abstract void onStart();
    public abstract void onSuccess(T data);
    public void onFail(String message) {
        FToast.error(message);
    }
    public abstract void onFinish();
}
