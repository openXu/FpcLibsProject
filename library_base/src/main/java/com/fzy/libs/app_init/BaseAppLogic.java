package com.fzy.libs.app_init;


import android.content.res.Configuration;
import android.util.Log;

import com.fzy.libs.base.BaseApplication;

/**
 * Author: openXu
 * Time: 2019/2/23 14:42
 * class: BaseAppLogic
 * Description: 模块初始化基类，如果module中需要在Application创建时进行初始化操作，
 *              只需要继承该类，覆盖onCreate()进行初始化，并将实现类添加到ModuleInitManager中
 */
public class BaseAppLogic {

    protected String TAG;

    protected BaseApplication mApplication;

    public BaseAppLogic(){
        TAG = getClass().getSimpleName();
    }

    public void setApplication(BaseApplication application){
        mApplication = application;
    }

    public void onCreate(){
        Log.d(TAG, TAG+" --> app module init");

    }
    public void onTerminate() {}
    public void onLowMemory() {}
    public void onTrimMemory(int level) {}
    public void onConfigurationChanged(Configuration newConfig) {}

}
