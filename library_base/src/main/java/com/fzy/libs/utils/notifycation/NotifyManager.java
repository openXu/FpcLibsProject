package com.fzy.libs.utils.notifycation;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.fzy.libs.R;
import com.fzy.libs.base.BaseApplication;
import com.fzy.libs.utils.FLog;

import java.util.List;
import java.util.Random;

import androidx.core.app.NotificationCompat;


public class NotifyManager {

    private volatile static NotifyManager INSTANCE;
    private static Context context;
    private NotifyManager() {
        initNotifyManager();
    }
    static {
        context = BaseApplication.getApplication().getApplicationContext();
    }
    public static NotifyManager getInstance() {
        if (INSTANCE == null) {
            synchronized (NotifyManager.class) {
                if (INSTANCE == null)
                    INSTANCE = new NotifyManager();
            }
        }
        return INSTANCE;
    }

    private NotificationManager manager;
    private String channeId;

    //初始化通知栏配置
    private void initNotifyManager() {
        this.context = context.getApplicationContext();
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        channeId = context.getPackageName();
        setChannel(manager, channeId, context.getPackageName());
    }

    private void setChannel(NotificationManager manager, String channeId, String channelName) {
        FLog.i("设置通知渠道ID="+channeId+"   name="+channelName);
        if (TextUtils.isEmpty(channeId) || TextUtils.isEmpty(channelName)) {
            FLog.i("NotifyCompatYc:  ".concat("安卓8.0的通知兼容库中 channeId 与 channelName 不能为empty"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //第三个参数设置通知的优先级别
            NotificationChannel channel =
                    new NotificationChannel(channeId, channelName, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("掌上119通知类别");
            channel.canBypassDnd();//是否可以绕过请勿打扰模式
            channel.canShowBadge();//是否可以显示icon角标
            channel.setBypassDnd(true);//设置绕过免打扰 “lockscreenVisibility”和“setBypassDnd”是无法生效
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_SECRET);

            channel.setShowBadge(true);
            channel.enableLights(true);//是否显示通知闪灯
            channel.setLightColor(Color.RED);//设置闪光灯颜色
            channel.enableVibration(true);//收到小时时震动提示
            channel.getAudioAttributes();//获取设置铃声设置
//            channel.setVibrationPattern(new long[]{100, 200, 100});//设置震动模式
            manager.createNotificationChannel(channel);
        }
    }

    public void clearNotify() {
        manager.cancelAll();
    }

    /**3种通知类型*/
    public static final int Notify_style_general = 1;  //发送普通通知
    public static final int Notify_style_pucker = 2;   //发送折叠式通知
    /**5.0新增加方式，可在当前界面顶部显示几秒后消失*/
    public static final int Notify_style_fullscreen = 3;//发送悬挂式通知
    public void showNotify(int style, NotifyMsg msg) {
        FLog.i("显示通知栏："+msg);
        //设置通知栏点击事件处理
//        Intent openIntent  = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        Intent intent  = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));

//        if(isAppInForeground(context) && !TextUtils.isEmpty(ConfigUtil.instance.getRuntimeContext().getUserID())){
//            openIntent = new Intent(context, MainActivityNew.class);
//            LogUtil.i(TAG, "应用程序已经开启，点击消息跳到主界面");
//        } else {
//            openIntent = new Intent(context, SplashActivity.class);
//            LogUtil.i("TAG", "应用程序没有开启，点击消息打开SplashActivity");
//        }
//        openIntent.putExtra("MessageInfo", messageInfo);

        NotificationCompat.Builder builder = getBaseBuilder();

        /**
         * 5.0加入显示等级：
         * Notification.VISIBILITY_PRIVATE  只有在没有锁屏时显示通知
         * Notification.VISIBILITY_PUBLIC   任何情况下都会显示通知
         * Notification.VISIBILITY_SECRET   在pin、password等安全锁和没有锁屏的情况下才能显示通知
         */
        builder.setVisibility(Notification.VISIBILITY_PUBLIC);

        int msgId =  Math.abs(new Random().nextInt());
        PendingIntent pendingIntent = PendingIntent.getActivity(context, msgId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        switch (style){
            case  Notify_style_general:   //发送普通通知
                break;
            case Notify_style_pucker:
                //视图显示进程和创建进程不子啊同一个进程，需要使用RemoteViews创建自定义视图
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notify_pucker_layout);
                builder.setCustomBigContentView(remoteViews);  //指定展开时的视图
                break;
            case Notify_style_fullscreen:
                Intent hangIntent = new Intent();
                hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                hangIntent.setClass(context, NotifyActivity.class);
                PendingIntent hangPendingIntent = PendingIntent.getActivity(context, msgId, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                //setFullScreenIntent()将Notification变为悬挂式Notification
                builder.setFullScreenIntent(null, true);
                break;
        }

        builder.setWhen(System.currentTimeMillis()); // 什么时候提醒的
        builder.setContentTitle(msg.getTitle());// 设置通知的标题
        builder.setContentText(msg.getContent());// 设置通知的内容

        manager.notify(msgId, builder.build());
    }



    private NotificationCompat.Builder getBaseBuilder(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channeId);
        builder.setPriority(Notification.PRIORITY_DEFAULT);// 设置通知栏的优先级
        builder.setAutoCancel(true);   // 设置点击可消失
        builder.setDefaults(Notification.DEFAULT_ALL);  // 设置是否震动等
        builder.setSmallIcon(R.mipmap.ic_launcher);     // 设置icon
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        return builder;
    }

    private boolean isAppInForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                return appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
            }
        }
        return false;
    }
}