package com.fzy.mbase.presenter;

import com.fzy.libs.http.HttpCallBack;
import com.fzy.libs.http.NetworkManager;
import com.fzy.mbase.entry.User;

import java.util.HashMap;
import java.util.Map;

public class LoginPersenter {

    public void login(LoginCallback callback){
        Map<String, String> params = new HashMap<>();
        params.put("date", "2019-02-22");
        NetworkManager.getInstance().doGet("dsapi/", params, new HttpCallBack(){
            @Override
            public void onSeccuce(Object data) {
                callback.onSeccuce(new User("openxuLogin", "123456"));
            }
            @Override
            public void onError(String msg) {

            }
        });

    }

    public interface LoginCallback{
        void onSeccuce(User user);
    }
}
