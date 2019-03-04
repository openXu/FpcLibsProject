package com.fzy.libs.http.error;

/**
 * Author: openXu
 * Time: 2019/3/4 11:54
 * class: HttpError
 * Description:
 */
public class HttpError extends Exception {

    private int code;
    private String massage;

    public HttpError(int code, String massage) {
        super();
        this.code = code;
        this.massage = massage;
    }

    @Override
    public String toString() {
        return "HttpError{" + code +":" + massage+ '}';
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

    /**
     * 当Http请求发生错误时，返回 给用户提示的 信息
     * 由于massage 是具体的错误类型，这是方便我们定位错误的，但是不能直接暴露给用户
     */
    public String getToastMsg(){
        if(code >= 1000){
            return "请检查网络";
        } else if(code >= 400){
            /*
             * 这一类型的错误是应该在发布之前解决的，用户不会看见（不排除服务器修改相关接口后）
             * 当用户反馈此消息需要紧急跟踪相关异常信息迅速解决
             */
            return "服务器开小差了";
        } else {
            return massage;
        }
    }
}
