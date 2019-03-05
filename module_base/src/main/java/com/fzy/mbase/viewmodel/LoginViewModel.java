package com.fzy.mbase.viewmodel;

import android.app.Application;

import com.fzy.libs.base.BaseViewModel;
import com.fzy.libs.http.HttpCallBack;
import com.fzy.libs.http.NetworkManager;
import com.fzy.libs.http.data.FzyResponse;
import com.fzy.libs.http.rx.BaseOberver;
import com.fzy.libs.utils.FLog;
import com.fzy.mbase.bean.Anim;
import com.fzy.mbase.bean.Qg;
import com.fzy.mbase.bean.QtBean;
import com.fzy.mbase.bean.QtListBean;
import com.fzy.mbase.bean.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
//        FLog.i("登陆");
//        testget();
//        fzyGet();
//        fzyGet1();
//        fzyGet2();
        fzyGet3();
    }

    private void testget(){
        Map<String, String> params = new HashMap<>();
        params.put("date", "2019-02-22");
     /*   NetworkManager.getInstance().doGetByRx("dsapi/", params, new BaseOberver<User>() {
            @Override
            public void onStart() {
                showDialog();
            }
            @Override
            public void onSuccess(User response) {
                user.setValue(response);
            }

            @Override
            public void onFinish() {
                dismissDialog();
            }
        });*/
    }

    private void fzyGet(){
        Map<String, String> params = new HashMap<>();
        params.put("companyId", "e3c6f838-13df-11e9-bf3a-fa163e4635ff");
        // "{\"code\":100, \"massage\":\"请求成功\", \"data\":[{\"userName\":\"11111\", \"password\":\"11111\"}, {\"userName\":\"222222\", \"password\":\"222222\"}, {\"userName\":\"2333333\", \"password\":\"33333\"}]}}";
        FzyResponse response = new FzyResponse(100, "请求成功", new Anim("name", "psw", new Qg("眼睛")));
        NetworkManager.getInstance().doGetByRx("demo", params, new Gson().toJson(response), new BaseOberver<Anim>() {
            @Override
            public void onStart() { showDialog();}
            @Override
            public void onSuccess(Anim data) {
                FLog.i("fzyGet返回数据："+data);
//                user.setValue(data);
            }
            @Override
            public void onFinish() { dismissDialog();}
        });
    }
    private void fzyGet1(){
        FzyResponse response = new FzyResponse(100, "请求成功", new QtBean(new Anim("name1", "psw1", new Qg("屁股")), "班级1"));
        Map<String, String> params = new HashMap<>();
        params.put("companyId", "e3c6f838-13df-11e9-bf3a-fa163e4635ff");
        NetworkManager.getInstance().doGetByRx("demo", params, new Gson().toJson(response), new BaseOberver<QtBean>() {
            @Override
            public void onStart() { showDialog(); }
            @Override
            public void onSuccess(QtBean data) {
                FLog.i("fzyGet1返回数据："+data);
            }
            @Override
            public void onFinish() { dismissDialog();  }
        });
    }
    private void fzyGet2(){
        List<Anim> anims = new ArrayList<>();
        anims.add(new Anim("name2", "psw2", new Qg("鼻子")));
        anims.add(new Anim("name  2", "psw   2", new Qg("嘴巴")));
        FzyResponse response = new FzyResponse(100, "请求成功", new QtListBean(anims, "班级2"));

        Map<String, String> params = new HashMap<>();
        params.put("companyId", "e3c6f838-13df-11e9-bf3a-fa163e4635ff");
        NetworkManager.getInstance().doGetByRx("demo", params, new Gson().toJson(response), new BaseOberver<QtListBean>() {
            @Override
            public void onStart() {  showDialog();  }
            @Override
            public void onSuccess(QtListBean data) {
                FLog.i("fzyGet2返回数据："+data);}
            @Override
            public void onFinish() {dismissDialog();}
        });
    }
    private void fzyGet3(){
        List<Anim> anims = new ArrayList<>();
        anims.add(new Anim("name2", "psw2", new Qg("鼻子")));
        anims.add(new Anim("name  2", "psw   2", new Qg("嘴巴")));
        FzyResponse response = new FzyResponse(100, "请求成功", anims);

        Map<String, String> params = new HashMap<>();
        params.put("companyId", "e3c6f838-13df-11e9-bf3a-fa163e4635ff");
        NetworkManager.getInstance().doGetByRx("demo", params, new Gson().toJson(response), new BaseOberver<List<Anim>>() {
            @Override
            public void onStart() {  showDialog();  }
            @Override
            public void onSuccess(List<Anim> data) {
                FLog.i("fzyGet3返回数据："+data);}
            @Override
            public void onFinish() {dismissDialog();}
        });
    }
}