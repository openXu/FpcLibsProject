package com.fzy.libs.base;

import android.app.Application;
import android.content.res.Configuration;

import com.fzy.libs.BuildConfig;
import com.fzy.libs.init.BaseAppLogic;
import com.fzy.libs.init.ModuleInitManager;
import com.fzy.libs.utils.FLog;

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
    private List<BaseAppLogic> logicClassList = new ArrayList<>();

    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        logicCreate();

        FLog.i("Application被创建："+this);
        FLog.i("appType："+BuildConfig.appType);
    }

    public static Application getApplication(){
        return application;
    }

    private void logicCreate(){

        for(String name : ModuleInitManager.initLogicNames){
            try{
                //使用反射初始化调用
                Class clazz = Class.forName(name);
                BaseAppLogic appLogic = (BaseAppLogic)clazz.newInstance();
                appLogic.setApplication(this);
                logicClassList.add(appLogic);
                appLogic.onCreate();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
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
