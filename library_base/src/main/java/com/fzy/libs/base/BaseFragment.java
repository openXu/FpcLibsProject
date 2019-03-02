package com.fzy.libs.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

/**
 * Author: openXu
 * Time: 2019/2/28 16:24
 * class: BaseFragment
 * Description:
 */
public abstract class BaseFragment <V extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {

    protected V binding;
    protected VM viewModel;
    private int viewModelId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                getContentView(inflater, container, savedInstanceState), container, false);
        viewModelId = getViewModelVariableId();
        viewModel = initViewModel();
        if (viewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            viewModel = (VM) ViewModelProviders.of(this).get(modelClass);
        }
        binding.setVariable(viewModelId, viewModel);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //页面数据初始化方法
        initData();
    }


    public void initParam() {}

    /**
     * 初始化根布局
     * @return 布局layout的id
     */
    public abstract int getContentView(LayoutInflater inflater,
                                        @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**获取ViewModel的VariableId*/
    protected abstract int getViewModelVariableId();
    //页面数据初始化方法
    protected abstract void initData();
    /**
     * 初始化ViewModel
     * @return 继承BaseViewModel的ViewModel
     */
    public VM initViewModel() {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除ViewModel生命周期感应
        getLifecycle().removeObserver(viewModel);
        if(binding != null){
            binding.unbind();
        }
    }


}
