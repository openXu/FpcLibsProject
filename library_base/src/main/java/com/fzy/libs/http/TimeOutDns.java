package com.fzy.libs.http;

import com.fzy.libs.utils.FLog;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableOnErrorNext;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Dns;

/**
 * author : openXu
 * created time : 19/3/2 下午9:38
 * blog : http://blog.csdn.net/xmxkf
 * github : http://blog.csdn.net/xmxkf
 * class name : TimeOutDns
 * discription :
 */
public class TimeOutDns implements Dns {

    private long timeout;
    private TimeUnit unit;

    public TimeOutDns(long timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
    }

    @Override
    public List<InetAddress> lookup(final String hostname) throws UnknownHostException {
      /*  if (hostname == null) {
            throw new UnknownHostException("hostname == null");
        } else {
            try {
                return Arrays.asList(InetAddress.getAllByName(hostname));
            } catch (NullPointerException var4) {
                UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + hostname);
                unknownHostException.initCause(var4);
                throw unknownHostException;
            }
        }*/


        FLog.w("解析DNS1："+Thread.currentThread());
        if (hostname == null) {
            throw new UnknownHostException("hostname == null");
        } else {
            try {


                FutureTask<List<InetAddress>> task = new FutureTask<>(
                        new Callable<List<InetAddress>>() {
                            @Override
                            public List<InetAddress> call() throws Exception {
                                return Arrays.asList(InetAddress.getAllByName(hostname));
                            }
                        });
                new Thread(task).start();
                List<InetAddress> addrs = task.get(timeout, unit);


              /* FLog.w("解析DNS1:"+Thread.currentThread());
                Observable.timer(timeout, unit)
                        .subscribeOn(Schedulers.)
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                FLog.w("解析DNS2:"+Thread.currentThread());
                                throw new Exception("解析DNS超时");
                            }
                        });
                FLog.w("解析DNS3:"+Thread.currentThread());
                List<InetAddress> addrs = Arrays.asList(InetAddress.getAllByName(hostname));
                FLog.w("解析DNS4:"+addrs.get(0));*/
                return addrs;
            } catch (Exception var4) {
                UnknownHostException unknownHostException =
                        new UnknownHostException("Broken system behaviour for dns lookup of " + hostname);
                unknownHostException.initCause(var4);
                throw unknownHostException;
            }
        }
    }
}
