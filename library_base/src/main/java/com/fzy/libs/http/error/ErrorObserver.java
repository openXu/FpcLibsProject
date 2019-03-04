package com.fzy.libs.http.error;

import com.fzy.libs.utils.FLog;
import com.fzy.libs.utils.toasty.FToast;

import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * Author: openXu
 * Time: 2019/3/4 16:19
 * class: ErrorObserver
 * Description:
 */
public class ErrorObserver implements Function<Throwable, ResponseBody> {
    @Override
    public ResponseBody apply(Throwable throwable) throws Exception {
        HttpError error = HttpErrorHandle.handleError(throwable);
        FLog.e("Http请求错误："+error);
        //给用户错误提示
        FToast.error(error.getToastMsg());
        return null;
    }
}
