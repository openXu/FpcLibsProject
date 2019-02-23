package com.fzy.libs.base;


import android.content.res.Configuration;

/**
 * Author: openXu
 * Time: 2019/2/23 14:42
 * class: BaseAppLogic
 * Description: 模块初始化基类，如果module中需要在Application创建时进行初始化操作，
 *              只需要继承该类，覆盖onCreate()进行初始化即可
 */
public class BaseAppLogic {

    protected BaseApplication mApplication;

    public BaseAppLogic(){}

    public void setApplication(BaseApplication application){
        mApplication = application;
    }

    public void onCreate(){}
    public void onTerminate() {}
    public void onLowMemory() {}
    public void onTrimMemory(int level) {}
    public void onConfigurationChanged(Configuration newConfig) {}

}
