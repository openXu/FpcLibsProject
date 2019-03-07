
## 1、Toast
```JAVA
    //如果有必要可以在任何地方设置，随时生效
    FzyToast.Config.getInstance()
                    .tintIcon(true) // optional (apply textColor also to the icon)
                    .setToastTypeface(Typeface.create("sans-serif-condensed", Typeface.ITALIC)) //设置字体样式（艺术字）
                    .setTextSize(15) // 设置文字大小sp
                    .allowQueue(false) // 是否排队等待展示完毕（后面不会覆盖前面）
                    .apply();

    //恢复默认设置
    FzyToast.Config.getInstance()reset()

    //使用
    FzyToast.error("错误提示", Toast.LENGTH_SHORT, true);
    FzyToast.success("成功提示", Toast.LENGTH_SHORT, true);
    FzyToast.info("信息提示", Toast.LENGTH_SHORT, false);
    FzyToast.warning("警告提示", Toast.LENGTH_SHORT, true);
    FzyToast.normal("普通提示");
```

## 2、Log
```JAVA
    FzyLog.i("xxxxxxxxxx");
    FzyLog.v("xxxxxxxxxx");
    FzyLog.d("xxxxxxxxxx");
    FzyLog.e("xxxxxxxxxx");
    FzyLog.w("xxxxxxxxxx");
    FzyLog.json("{\"code\":\"200\", \"data\":{}, \"msg\":\"请求数据成功\"}");
```


## 3、Dialog
https://github.com/afollestad/material-dialogs

## 4、刷新
https://github.com/scwang90/SmartRefreshLayout/tree/master

## Data Binding

> 开启Data Binding：在Module的build.gradle中添加下面配置即可
```xml
android {
    ...
    dataBinding{
        enabled = true
    }
}
```




