package com.fzy.mbase.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzy.libs.base.BaseActivity;
import com.fzy.libs.router.RouterActivityPath;
import com.fzy.libs.router.RouterActivityPath_Test;

import com.fzy.mbase.R;
import com.fzy.mbase.databinding.ActivityLoginBinding;
import com.fzy.mbase.viewmodel.LoginViewModel;

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = RouterActivityPath.Common.PAGE_LOGIN)
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected int getContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected int getViewModelVariableId() {
        return 0;
    }

    @Override
    protected void initData() {
        viewModel.user.observe(this, user -> {
            binding.setUser(user);
//            startActivity(RouterActivityPath_Test.PAGE_MAIN);
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
