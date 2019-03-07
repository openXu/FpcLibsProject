package com.fzy.minspection.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Author: openXu
 * Time: 2019/2/23 18:06
 * class: Module
 * Description:
 */
@Entity
public class Module {
    @Id(autoincrement = true)
    private Long id;
    private String ModuleID;
    private String TaskID;
    @Generated(hash = 1772698787)
    public Module(Long id, String ModuleID, String TaskID) {
        this.id = id;
        this.ModuleID = ModuleID;
        this.TaskID = TaskID;
    }
    @Generated(hash = 300059863)
    public Module() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getModuleID() {
        return this.ModuleID;
    }
    public void setModuleID(String ModuleID) {
        this.ModuleID = ModuleID;
    }
    public String getTaskID() {
        return this.TaskID;
    }
    public void setTaskID(String TaskID) {
        this.TaskID = TaskID;
    }

}

