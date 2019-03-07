package com.fzy.minspection.UI;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fzy.libs.base.BaseActivity1;
import com.fzy.libs.router.RouterPath;
import com.fzy.minspection.R;
import com.fzy.minspection.databinding.ActivityTaskBinding;

import androidx.databinding.DataBindingUtil;


@Route(path = RouterPath.Inspection.PAGE_TASK)
public class TaskActivity1 extends BaseActivity1 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTaskBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_task);

        binding.btnLogin.setOnClickListener(v->{
        });
    }
}
