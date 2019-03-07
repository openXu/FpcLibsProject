package com.fzy.mbase.activity;


import android.os.Bundle;

import com.fzy.libs.base.BaseActivity;
import com.fzy.libs.router.RouterPath;
import com.fzy.mbase.R;
import com.fzy.mbase.BR;
import com.fzy.mbase.databinding.ActivitySplashBinding;
import com.fzy.mbase.viewmodel.SplashViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: openXu
 * Time: 2019/3/1 12:28
 * class: SplashActivity
 * Description:
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {


    @Override
    protected int getContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    protected int getViewModelVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initData() {

        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    startActivity(RouterPath.MBase.PAGE_LOGIN);
                    finish();
                });

    }
}
