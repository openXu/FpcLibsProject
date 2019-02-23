package com.fzy.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Author: openXu
 * Time: 2019/2/23 18:06
 * class: App1Bean
 * Description:
 */
@Entity
public class App2Bean1 {
    @Id(autoincrement = true)
    private Long id;
    private String MessageID;

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

