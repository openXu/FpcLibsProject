package com.fzy.libs.base;

import android.app.Application;

import com.fzy.libs.utils.FzyLog;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/**
 * Author: openXu
 * Time: 2019/2/27 16:49
 * class: BaseViewModel
 * Description:
 * 1、ViewModel是为界面组件提供数据，并可在配置变更后继续存在的对象，
 *    用于管理相关业务逻辑和数据，充当Controller。
 * 2、需要继承ViewModel类，并将界面需要的数据放到ViewModel中
 * 3、由于ViewModel生命周期比Activity&Fragment长，由获取ViewModel实例时传递给ViewModelProvider的Lifecycle决定的
 *    (activity.onDestroy() or fragment.detached())，所以不能引用他们的实例。
 * 4、如果ViewModel需要Application的context（如获取系统服务），可以继承AndroidViewModel，
 *    并拥有一个构造器接收Application。
 *
 * 个人理解：ViewModel相当于MVP模式中的P，在MVP模式中，P需要持有V的引用才能更新界面，
 *          这样会导致生命周期错乱造成内存泄漏，而且需要创建很多IView接口，不方便管理。
 *          而在MVVM中，IView的功能由LiveData代替了，并且更好的处理了生命周期问题，
 *          VM中也不需要持有V的引用，还能自己管理生命周期。
 *

 *
==================================Architecture Components=====================================================
 * https://mp.weixin.qq.com/s/9rC_5GhdAA_EMEbWKJT5vQ
 * App 架构指南：https://developer.android.google.cn/topic/libraries/architecture/guide.html
 * Android 架构组件官网：https://developer.android.google.cn/topic/libraries/architecture/index.html
 *
 * 架构组件（Architecture Components）包含4个部分：
 * Lifecycles：定义了有生命周期的Android组件，实现对生命周期管理，生命周期库是其他架构组件（如 LiveData）的基础。
 *
 * LiveData：LiveData 是一款基于观察者模式的可感知生命周期的核心组件。LiveData作为Observable可以被界面UI观察 （Observer），
 *           当 LiveData 所持有的数据改变时，它会通知相应的界面代码进行更新。同时，LiveData 持有界面Activity lifecycle
 *           的引用，这意味着它会在界面代码（LifecycleOwner）的生命周期处于 started 或 resumed 时作出相应更新，
 *           而在 LifecycleOwner 被销毁时停止更新。通过 LiveData，开发者可以方便地构建安全性更高、性能更好的高响应度用户界面。
 *
 * ViewModel：ViewModel 将视图的数据和逻辑从具有生命周期特性的实体（如 Activity 和 Fragment）中剥离开来。直到关联的 Activity
 *            或 Fragment 完全销毁时，ViewModel 才会随之消失，也就是说，即使在旋转屏幕导致 Fragment 被重新创建等事件中，
 *            视图数据依旧会被保留。ViewModels 不仅消除了常见的生命周期问题，而且可以帮助构建更为模块化、更方便测试的用户界面
 *
 * Room ： 一款简单好用的对象映射层。它和 SQLite 有一样强大的功能，但是节省了很多重复编码的麻烦事儿。它的一些功能，如编译时的数据
 *        查询验证、内置迁移支持等，让开发者能够更简单地构建健壮的持久层。而且 Room 可以和 LiveData 集成在一起，提供可观测数据库
 *        并感知生命周期的对象。Room 集简洁、强大和可靠性为一身，在管理本地储存上表现卓越，我们强烈推荐各位开发者试用一下。
 *
 * 其中Lifecycle：
 * LifecycleOwner：是具有生命周期的对象，比如Activity和Fragment
 *
 * LifecycleObserver：生命周期观察者，比如LiveData。他用来观测LifecycleOwner，并在LifecycleOwner生命周期变化时收到通知。
 *
 *
 */
public class BaseViewModel extends AndroidViewModel implements IBaseViewModel{

    public BaseViewModel(@NonNull Application application) {
        super(application);
        FzyLog.w("ViewModel创建");
    }
    //ON_ANY可以匹配所有生命周期方法
    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        FzyLog.i("匹配生命周期：onAny");
    }
    @Override
    public void onCreate() {
        FzyLog.i("匹配生命周期：onCreate");
    }
    @Override
    public void onStart() {
        FzyLog.i("匹配生命周期：onStart");
    }
    @Override
    public void onResume() {
        FzyLog.i("匹配生命周期：onResume");
    }
    @Override
    public void onPause() {
        FzyLog.i("匹配生命周期：onPause");
    }
    @Override
    public void onStop() {
        FzyLog.i("匹配生命周期：onStop");
    }
    @Override
    public void onDestroy() {
        FzyLog.i("匹配生命周期：onDestroy");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        FzyLog.e("ViewModel.onCleared()清除资源");
    }
}
