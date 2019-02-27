package com.fzy.libs.base;

import android.content.Context;
import android.os.Bundle;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

/**
 * Author: openXu
 * Time: 2019/2/27 15:51
 * class: BaseActivity
 * Description:
 *
 *
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected String TAG;
    protected Context mContext;

    protected V binding;
    protected VM viewModel;
    private int viewModelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        mContext = this;
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding(savedInstanceState);
        initData();

    }

    /**设置布局文件*/
    protected abstract int getContentView(Bundle savedInstanceState);
    /**获取ViewModel的VariableId*/
    protected abstract int getViewModelVariableId();
    /**
     * 初始化ViewModel
     * @return 继承BaseViewModel的ViewModel
     */
    public VM initViewModel() {
        return null;
    }

    //页面数据初始化方法
    protected abstract void initData();

    /** 注入DataBinding，初始化ViewModel */
    private void initViewDataBinding(Bundle savedInstanceState) {
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        binding = DataBindingUtil.setContentView(this, getContentView(savedInstanceState));
        viewModelId = getViewModelVariableId();
        viewModel = initViewModel();
        if (viewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                //获取第2个泛型的类文件
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            //通过ViewModelProviders，传入生命周期对象，获取ViewModel实例
            viewModel = (VM) ViewModelProviders.of(this).get(modelClass);
        }
        //关联ViewModel
        binding.setVariable(viewModelId, viewModel);
        //将ViewModel作为生命周期观测者，让其拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
//        viewModel.injectLifecycleProvider(this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
