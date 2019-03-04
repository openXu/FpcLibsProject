package com.fzy.libs.base;

import android.content.Context;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fzy.libs.utils.FLog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * Author: openXu
 * Time: 2019/2/27 15:51
 * class: BaseActivity
 * Description:
 *
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected String TAG;
    protected Context mContext;

    protected V binding;
    protected VM viewModel;
    private int viewModelId;

    private MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        mContext = this;
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding(savedInstanceState);
        registUIEventObserve();
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
        // TODO
        binding.setLifecycleOwner(this);
        //将ViewModel作为生命周期观测者，让其拥有View的生命周期感应.BaseViewModel实现了LifecycleObserver
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
//        viewModel.injectLifecycleProvider(this);
    }

    /**UI中注册观察者，观察ViewModel中控制UI事件的LiveData的变化*/
    private void registUIEventObserve() {
        //加载对话框显示
        viewModel.getUIEvent().event_dialog_loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                FLog.w("控制显示Dialog1："+aBoolean);
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                dialog = new MaterialDialog.Builder(mContext)
                        .content("请稍后...")
                        .progress(true,-1)//等待图标 true=圆形icon false=进度条
                        .cancelable(false)//不会被取消 （包括返回键和外部点击都无法取消）
                        .build();
                dialog.show();
            }
        });
        //对话框消失
        viewModel.getUIEvent().event_dialog_dismiss.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
            }
        });
        viewModel.getUIEvent().event_startactivity.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String path) {
                startActivity(path);
            }
        });
        //关闭界面
        viewModel.getUIEvent().event_finish.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                finish();
            }
        });
    }

    public void startActivity(String path){
        ARouter.getInstance().build(path).navigation();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除ViewModel生命周期感应
        getLifecycle().removeObserver(viewModel);
        if(binding != null)
            binding.unbind();
    }

}
