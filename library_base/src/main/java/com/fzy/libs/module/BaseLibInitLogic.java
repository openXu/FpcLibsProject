package com.fzy.libs.module;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fzy.libs.BuildConfig;
import com.fzy.libs.utils.FLog;
import com.squareup.leakcanary.LeakCanary;

/**
 * Author: openXu
 * Time: 2019/2/23 14:49
 * class: BaseLibInitLogic
 * Description: 基础库中需要在Application启动时初始化的内容，在onCreate()中完成初始化
 */
public class BaseLibInitLogic extends BaseAppLogic {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(mApplication)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
//        LeakCanary.install(mApplication);
        FLog.i("APP版本:"+BuildConfig.appType);
        //初始化阿里路由框架
        if (BuildConfig.DEBUG) {
            FLog.i("debug模式:"+BuildConfig.DEBUG);
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(mApplication); // 尽可能早，推荐在Application中初始化

    }
}
