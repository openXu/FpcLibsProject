package com.fzy.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: openXu
 * Time: 2019/2/23 18:06
 * class: App1Bean
 * Description:
 */
@Entity
public class App1Bean1 {
    @Id(autoincrement = true)
    private Long id;
    private String MessageID;

    @Generated(hash = 69515102)
    public App1Bean1(Long id, String MessageID) {
        this.id = id;
        this.MessageID = MessageID;
    }

    @Generated(hash = 1159247646)
    public App1Bean1() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageID() {
        return MessageID;
    }

    public void setMessageID(String messageID) {
        MessageID = messageID;
    }
}

