package com.fzy.libs.net.data;

/**
 * Author: openXu
 * Time: 2019/3/4 16:33
 * class: FzyResponse
 * Description: 服务器返回数据格式约定
 */
public class FzyResponse<T> {

    private int code;
    private String massage;
    private T data;

    public FzyResponse() {
    }

    public FzyResponse(int code, String massage, T data) {
        this.code = code;
        this.massage = massage;
        this.data = data;
    }

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

    /**
     * 判断服务器返回的数据是否正确（没有业务错误）
     * @return
     */
    public boolean isSuccess(){
        return code == 100;
    }
}
