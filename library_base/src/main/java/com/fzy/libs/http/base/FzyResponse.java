package com.fzy.libs.http.base;

/**
 * Author: openXu
 * Time: 2019/3/4 16:33
 * class: FzyResponse
 * Description:
 */
public class FzyResponse<T> {

    private int code;
    private String massage;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
