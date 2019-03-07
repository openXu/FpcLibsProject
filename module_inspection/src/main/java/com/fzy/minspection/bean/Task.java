package com.fzy.minspection.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Author: openXu
 * Time: 2019/2/23 18:06
 * class: Task
 * Description:
 */
@Entity
public class Task {
    @Id(autoincrement = true)
    private Long id;
    private String TaskID;
    @Generated(hash = 1344126809)
    public Task(Long id, String TaskID) {
        this.id = id;
        this.TaskID = TaskID;
    }
    @Generated(hash = 733837707)
    public Task() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTaskID() {
        return this.TaskID;
    }
    public void setTaskID(String TaskID) {
        this.TaskID = TaskID;
    }

}

