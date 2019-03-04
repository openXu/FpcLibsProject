package com.fzy.libs.http.base;

import com.fzy.libs.utils.FLog;

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
        FLog.i("onSubscribe开始订阅了");
    }
    @Override
    public void onComplete() {
        FLog.i("发射完成了onComplete");
    }
}
