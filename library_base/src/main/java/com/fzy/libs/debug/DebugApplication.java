package com.fzy.libs.debug;

import com.fzy.libs.base.BaseApplication;
import com.fzy.libs.config.BaseLibInitLogic;


/**
 * Created by goldze on 2018/6/25 0025.
 * debug包下的代码不参与编译，仅作为独立模块运行时初始化数据
 */

public class DebugApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void initLogic() {
        //初始化基础模块
        registerApplicationLogic(BaseLibInitLogic.class);
    }
}
