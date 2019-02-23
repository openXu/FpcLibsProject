package com.fzy.libs.http;

public interface HttpCallBack<T> {

    void onSeccuce(T data);
    void onError(String msg);

}
