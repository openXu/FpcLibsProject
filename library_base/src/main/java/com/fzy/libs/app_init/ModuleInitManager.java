package com.fzy.libs.app_init;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: openXu
 * Time: 2019/2/25 10:58
 * class: ModuleInitManager
 * Description: 各个模块初始化管理工具
 */
public class ModuleInitManager {
    private static class SingletonHolder {
        public static ModuleInitManager instance = new ModuleInitManager();
    }
    public static ModuleInitManager getInstance() {
        return SingletonHolder.instance;
    }

    private ModuleInitManager() {}

    public List<Class<? extends BaseAppLogic>> getInitLogics() {
        List<Class<? extends BaseAppLogic>> list = new ArrayList<>();
        //初始化基础模块
        list.add(BaseLibInitLogic.class);
        // TODO 添加模块初始化类

        return list;

    }

}
