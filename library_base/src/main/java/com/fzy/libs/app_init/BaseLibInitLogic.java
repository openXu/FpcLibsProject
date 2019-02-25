package com.fzy.libs.app_init;

import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fzy.libs.BuildConfig;
import com.fzy.libs.http.HttpClient;

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

        //网络工具初始化
        HttpClient.getInstance().init(mApplication);

        //初始化阿里路由框架
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            Toast.makeText(mApplication, "debug模式:"+BuildConfig.DEBUG, Toast.LENGTH_LONG).show();
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(mApplication); // 尽可能早，推荐在Application中初始化
    }
}
