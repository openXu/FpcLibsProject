package com.fzy.minspection.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.fzy.minspection.bean.Item;
import com.fzy.minspection.bean.Module;
import com.fzy.minspection.bean.Task;

import com.fzy.minspection.dao.ItemDao;
import com.fzy.minspection.dao.ModuleDao;
import com.fzy.minspection.dao.TaskDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig itemDaoConfig;
    private final DaoConfig moduleDaoConfig;
    private final DaoConfig taskDaoConfig;

    private final ItemDao itemDao;
    private final ModuleDao moduleDao;
    private final TaskDao taskDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        itemDaoConfig = daoConfigMap.get(ItemDao.class).clone();
        itemDaoConfig.initIdentityScope(type);

        moduleDaoConfig = daoConfigMap.get(ModuleDao.class).clone();
        moduleDaoConfig.initIdentityScope(type);

        taskDaoConfig = daoConfigMap.get(TaskDao.class).clone();
        taskDaoConfig.initIdentityScope(type);

        itemDao = new ItemDao(itemDaoConfig, this);
        moduleDao = new ModuleDao(moduleDaoConfig, this);
        taskDao = new TaskDao(taskDaoConfig, this);

        registerDao(Item.class, itemDao);
        registerDao(Module.class, moduleDao);
        registerDao(Task.class, taskDao);
    }
    
    public void clear() {
        itemDaoConfig.clearIdentityScope();
        moduleDaoConfig.clearIdentityScope();
        taskDaoConfig.clearIdentityScope();
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public ModuleDao getModuleDao() {
        return moduleDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

}
