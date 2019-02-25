package com.fzy.libs.router;

/**
 * Author: openXu
 * Time: 2019/2/25 11:14
 * class: RouterActivityPath
 * Description: ARouter路由路径注册
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
