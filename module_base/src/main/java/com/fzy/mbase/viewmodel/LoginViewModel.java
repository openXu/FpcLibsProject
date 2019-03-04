package com.fzy.mbase.viewmodel;

import android.app.Application;
import android.view.View;

import com.fzy.libs.base.BaseViewModel;
import com.fzy.libs.http.HttpCallBack;
import com.fzy.libs.http.NetworkManager;
import com.fzy.libs.http.base.BaseOberver;
import com.fzy.libs.utils.FLog;
import com.fzy.libs.utils.toasty.FToast;
import com.fzy.mbase.bean.User;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

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
//        FLog.i("登陆");
//        testget();
//        fzyGet();
        fzyGet1();
    }

    private void testget(){
        Map<String, String> params = new HashMap<>();
        params.put("date", "2019-02-22");
        NetworkManager.getInstance().doGet1("dsapi/", params, new HttpCallBack(){
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
        NetworkManager.getInstance().doGet1("demo", params ,new HttpCallBack(){
            @Override
            public void onSeccuce(Object data) {
                user.setValue(new User("openxuLogin", "123456"));
            }
            @Override
            public void onError(String msg) {

            }
        });
    }

    private void fzyGet1(){
        Map<String, String> params = new HashMap<>();

        params.put("companyId", "e3c6f838-13df-11e9-bf3a-fa163e4635ff");
//        NetworkManager.getInstance().doGet("demo", params, new HttpCallBack(){
        NetworkManager.getInstance().doGet2("demo", params, new BaseOberver<User>() {
            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                showDialog();
            }
            @Override
            public void onComplete() {
                super.onComplete();
                dismissDialog();
            }
            @Override
            public void onNext(User user) {
                FLog.w("onNext开始发射了:"+user);
            }
            @Override
            public void onError(Throwable e) {
                FLog.e("发射错误了onError "+e);
            }
        });
    }

}