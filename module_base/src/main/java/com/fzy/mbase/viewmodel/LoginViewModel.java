package com.fzy.mbase.viewmodel;

import android.app.Application;
import android.view.View;

import com.fzy.libs.base.BaseViewModel;
import com.fzy.libs.http.HttpCallBack;
import com.fzy.libs.http.NetworkManager;
import com.fzy.libs.utils.FLog;
import com.fzy.libs.utils.toasty.FToast;
import com.fzy.mbase.bean.User;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * Author: openXu
 * Time: 2019/2/26 9:48
 * class: LoginViewModel
 * Description:
 */
public class LoginViewModel extends BaseViewModel {

    public MutableLiveData<User> user = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String userName, String psw) {
        FToast.normal("登陆").show();
//        FLog.i("登陆");
        testget();
//        fzyGet();
    }

    private void testget(){
        Map<String, String> params = new HashMap<>();
        params.put("date", "2019-02-22");
        NetworkManager.getInstance().doGet("dsapi/", params, new HttpCallBack(){
            @Override
            public void onSeccuce(Object data) {
                user.setValue(new User("openxuLogin", "123456"));
            }
            @Override
            public void onError(String msg) {

            }
        });
    }

    private void fzyGet(){
        Map<String, String> params = new HashMap<>();

        params.put("CompanyId", "e3c6f838-13df-11e9-bf3a-fa163e4635ff");
//        NetworkManager.getInstance().doGet("demo", params, new HttpCallBack(){
        NetworkManager.getInstance().doGet("demo", params, new HttpCallBack(){
            @Override
            public void onSeccuce(Object data) {
                user.setValue(new User("openxuLogin", "123456"));
            }
            @Override
            public void onError(String msg) {

            }
        });
    }

}