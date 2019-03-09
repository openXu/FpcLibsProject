package com.fzy.mbase.module;

import com.fzy.libs.module.BaseAppLogic;
import com.fzy.libs.utils.FLog;
import com.fzy.mbase.BuildConfig;

/**
 * Author: openXu
 * Time: 2019/2/23 14:49
 * class: BaseLibInitLogic
 * Description: 需要在Application启动时初始化的内容，在onCreate()中完成初始化
 */
public class ModuleBaseInitLogic extends BaseAppLogic {

    @Override
    public void onCreate() {
        super.onCreate();
        FLog.i("APP版本:"+BuildConfig.appType);
    }
}
