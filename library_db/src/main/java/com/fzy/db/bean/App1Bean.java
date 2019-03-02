package com.fzy.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: openXu
 * Time: 2019/2/23 18:06
 * class: App1Bean
 * Description:
 */
@Entity
public class App1Bean {
    @Id(autoincrement = true)
    private Long id;
    private String MessageID;

    @Generated(hash = 1502107731)
    public App1Bean(Long id, String MessageID) {
        this.id = id;
        this.MessageID = MessageID;
    }

    @Generated(hash = 1688170069)
    public App1Bean() {
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

