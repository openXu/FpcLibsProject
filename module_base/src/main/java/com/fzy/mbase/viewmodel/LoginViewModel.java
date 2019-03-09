package com.fzy.mbase.viewmodel;

import android.app.Application;

import com.fzy.libs.base.BaseViewModel;
import com.fzy.libs.net.NetworkManager;
import com.fzy.libs.net.data.FzyResponse;
import com.fzy.libs.net.rx.BaseOberver;
import com.fzy.libs.utils.FLog;
import com.fzy.mbase.bean.User;
import com.google.gson.Gson;

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
        fzyGet();
    }


    private void fzyGet(){
        Map<String, String> params = new HashMap<>();
        FzyResponse response = new FzyResponse(100, "请求成功", new User("name", "psw"));


//        params.put("command", "打开巡查页面");
//        NetworkManager.getInstance().doGetByRx("VoiceControl.aspx", params, new Gson().toJson(response), new BaseOberver<User>() {
        params.put("companyId", "e3c6f838-13df-11e9-bf3a-fa163e4635ff");
        NetworkManager.getInstance().doGetByRx("demo", params, new Gson().toJson(response), new BaseOberver<User>() {
            @Override
            public void onStart() { showDialog();}
            @Override
            public void onSuccess(User data) {
                FLog.i("fzyGet返回数据："+data);
                user.setValue(data);
            }
            @Override
            public void onFinish() { dismissDialog();}
        });
    }
}