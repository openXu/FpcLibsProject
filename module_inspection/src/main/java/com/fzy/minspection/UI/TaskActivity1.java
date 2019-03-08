package com.fzy.minspection.UI;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzy.libs.base.BaseActivity;
import com.fzy.libs.router.RouterPath;
import com.fzy.minspection.R;
import com.fzy.minspection.BR;
import com.fzy.minspection.databinding.ActivityTaskBinding;
import com.fzy.minspection.viewmodel.TaskViewModel;


@Route(path = RouterPath.Inspection.PAGE_TASK)
public class TaskActivity1 extends  BaseActivity<ActivityTaskBinding, TaskViewModel>  {


    @Override
    protected int getContentView(Bundle savedInstanceState) {
        return R.layout.activity_task;
    }

    @Override
    protected int getViewModelVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initData() {
        binding.btnLogin.setOnClickListener(v->{
        });
    }
}
