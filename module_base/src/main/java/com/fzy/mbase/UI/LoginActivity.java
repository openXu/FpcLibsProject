package com.fzy.mbase.UI;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fzy.libs.base.BaseActivity;
import com.fzy.libs.router.RouterActivityPath;
import com.fzy.mbase.presenter.LoginPersenter;

import androidx.databinding.DataBindingUtil;
import om.fzy.mbase.R;
import om.fzy.mbase.databinding.ActivityLoginBinding;
// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = RouterActivityPath.Common.PAGE_LOGIN)
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.btnLogin.setOnClickListener(v->{
            new LoginPersenter().login(user->{
                binding.setUser(user);

                // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
                ARouter.getInstance().build(RouterActivityPath.Inspection.PAGE_TASK).navigation();

// 2. 跳转并携带参数
//            ARouter.getInstance().build("/test/1")
//                    .withLong("key1", 666L)
//                    .withString("key3", "888")
//                    .withObject("key4", new Test("Jack", "Rose"))
//                    .navigation();

            });
        });

    }
}
