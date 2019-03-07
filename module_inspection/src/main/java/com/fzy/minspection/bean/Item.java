package com.fzy.minspection.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Author: openXu
 * Time: 2019/2/23 18:06
 * class: Item
 * Description:
 */
@Entity
public class Item {
    @Id(autoincrement = true)
    private Long id;
    private String ItemID;
    private String ModuleID;
    private String TaskID;
    @Generated(hash = 903559847)
    public Item(Long id, String ItemID, String ModuleID, String TaskID) {
        this.id = id;
        this.ItemID = ItemID;
        this.ModuleID = ModuleID;
        this.TaskID = TaskID;
    }
    @Generated(hash = 1470900980)
    public Item() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getItemID() {
        return this.ItemID;
    }
    public void setItemID(String ItemID) {
        this.ItemID = ItemID;
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

