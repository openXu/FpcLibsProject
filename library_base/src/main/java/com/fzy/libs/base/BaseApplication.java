package com.fzy.libs.base;

import android.app.Application;
import android.content.res.Configuration;

import com.fzy.libs.app_init.BaseAppLogic;
import com.fzy.libs.app_init.ModuleInitManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: openXu
 * Time: 2019/2/23 14:49
 * class: BaseApplication
 * Description: Application请继承该基类
 */
public abstract class BaseApplication extends Application {

    /**各module初始化类集合*/
    private List<Class<? extends BaseAppLogic>> logicList = new ArrayList<>();
    private List<BaseAppLogic> logicClassList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        //添加模块初始化类
        logicList.addAll(ModuleInitManager.getInstance().getInitLogics());
        logicCreate();
    }

    private void logicCreate(){
        for(Class<? extends BaseAppLogic> logicClass : logicList){
            try{
                //使用反射初始化调用
                BaseAppLogic appLogic = logicClass.newInstance();
                appLogic.setApplication(this);
                logicClassList.add(appLogic);
                appLogic.onCreate();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    //终止应用程序对象时调用，不保证一定被调用
    @Override
    public void onTerminate() {
        super.onTerminate();
        for(BaseAppLogic logic : logicClassList){
            logic.onTerminate();
        }
    }

    //当后台程序终止且资源还匮乏时会调用这个方法
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for(BaseAppLogic logic : logicClassList){
            logic.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for(BaseAppLogic logic : logicClassList){
            logic.onTrimMemory(level);
        }
    }

    //配置改变时出发这个方法，比如屏幕旋转
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        for(BaseAppLogic logic : logicClassList){
            logic.onConfigurationChanged(newConfig);
        }
    }
}
