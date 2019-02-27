package com.fzy.libs.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Author: openXu
 * Time: 2019/2/27 16:36
 * class: IBaseViewModel
 * Description: LifecycleObserver将类标记为生命周期观察者，
 *              它没有任何方法，而是依赖于@OnLifecycleEvent注释。
 *
 *              ViewModel需要观察组件的生命周期，以方便更好的释放资源
 */
public interface IBaseViewModel extends LifecycleObserver {

    //ON_ANY可以匹配所有生命周期方法
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(LifecycleOwner owner, Lifecycle.Event event);

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate();

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume();

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause();

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy();


}
