package com.fzy.libs.router;

/**
 * 用于组件开发中，ARouter单Activity跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * Created by goldze on 2018/6/21
 */
public class RouterActivityPath {

    /**通用模块*/
    public static class Common {
        private static final String COMMON = "/common";
        /*主业务界面*/
        public static final String PAGE_LOGIN = COMMON +"/login";
    }

    /**巡检模块*/
    public static class Inspection {
        private static final String INSPECT = "/inspect";
        /*登录界面*/
        public static final String PAGE_TASK = INSPECT + "/Task";
    }

}
