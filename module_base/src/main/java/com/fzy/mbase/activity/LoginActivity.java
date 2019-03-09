package com.fzy.mbase.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fzy.libs.BuildConfig;
import com.fzy.libs.base.BaseActivity;
import com.fzy.libs.config.AppTypes;
import com.fzy.libs.router.RouterPath;

import com.fzy.libs.utils.FLog;
import com.fzy.mbase.R;
import com.fzy.mbase.BR;
import com.fzy.mbase.databinding.ActivityLoginBinding;
import com.fzy.mbase.viewmodel.LoginViewModel;

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = RouterPath.MBase.PAGE_LOGIN)
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected int getContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected int getViewModelVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initData() {
        viewModel.user.observe(this, user -> {
            binding.setUser(user);

            //如果是测试app（使用AppTypes.TYPE_TEST == AppTypes.valueOf(BuildConfig.appType)判断版本）
            if(AppTypes.TYPE_TEST == AppTypes.valueOf(BuildConfig.appType)) {
                ARouter.getInstance().build(RouterPath.Common.PAGE_MAINSSS)
                        .withParcelable("User", user).navigation();
                finish();
            }
        });
        binding.btnLogin.setOnClickListener(v->{
            String name = binding.etName.getText().toString().trim();
            String psw = binding.etPsw.getText().toString().trim();
            viewModel.login(name, psw);
            if(name.length()>10 || name.length()<3){
                binding.tlName.setErrorEnabled(true);
                binding.tlName.setError("用户名长度至少为3，最大为10");
            }else if(psw.length()>8 || psw.length()<5){
                binding.tlPsw.setErrorEnabled(true);
                binding.tlPsw.setError("密码长度至少为5，最大为8");
            }else{
                binding.tlName.setErrorEnabled(false);
                binding.tlPsw.setErrorEnabled(false);
//                viewModel.login(name, psw);
            }

        });
    }
}
