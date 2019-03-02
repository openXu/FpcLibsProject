package com.fzy.libs.utils.notifycation;

/**
 * Author: openXu
 * Time: 2019/3/2 12:49
 * class: NotifyMsg
 * Description:
 */
public class NotifyMsg {

    private String title;
    private String content;

    public NotifyMsg(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
