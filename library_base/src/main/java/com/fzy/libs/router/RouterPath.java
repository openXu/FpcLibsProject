package com.fzy.libs.router;

/**
 * Author: openXu
 * Time: 2019/2/25 11:14
 * class: RouterPath
 * Description: ARouter路由路径注册
 *
 * 注意：
 * 1、不同的Module应该分不同的组，/groupName/pageName，也就是说groupName不能一样，否则可能导致
 * https://github.com/alibaba/ARouter/issues?utf8=%E2%9C%93&q=Multiple%20dex%20files%20define%20Lcom/alibaba/android/arouter/routes/ARouter
 *
 *
 */
public class RouterPath {
    public static class Common {
        private static final String GROUP = "/common";
        //主界面（app下主界面请用此路径注册）
        public static final String PAGE_MAINSSS = GROUP +"/main";
    }

    /**module_base*/
    public static class MBase {
        private static final String GROUP = "/mBase";
        /*登陆界面*/
        public static final String PAGE_LOGIN = GROUP +"/login";
    }

    /**module_inspection*/
    public static class Inspection {
        private static final String INSPECT = "/inspect";
        /*登录界面*/
        public static final String PAGE_TASK = INSPECT + "/Task";
    }
}
