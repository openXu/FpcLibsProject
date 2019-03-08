package com.fzy.mbase.activity;


import android.os.Bundle;
import android.view.MenuItem;

import com.fzy.libs.base.BaseViewModel;
import com.fzy.libs.utils.StatusBarUtil;
import com.fzy.mbase.BR;
import com.fzy.mbase.R;
import com.fzy.mbase.databinding.ActivityBaseMainBinding;
import com.fzy.libs.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public abstract class BaseMainActivity extends BaseActivity<ActivityBaseMainBinding, BaseViewModel> implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected Map<Integer, Fragment> mFragmentsMap = new HashMap<>();
    protected List<Integer> navigationIds = new ArrayList<>();

    @Override
    protected int getContentView(Bundle savedInstanceState) {
        return R.layout.activity_base_main;
    }

    @Override
    protected int getViewModelVariableId() {
        return BR.viewModel;
    }

    /**
     * 重写此方法
     */
    protected abstract void initFragment();

    protected void addFragment(int navigationId, Fragment fragment){
        navigationIds.add(navigationId);
        mFragmentsMap.put(navigationId, fragment);
    }

    @Override
    protected void initView() {
        super.initView();
        navigationIds.clear();
        mFragmentsMap.clear();
        initFragment();
        binding.navigation.setOnNavigationItemSelectedListener(this);

        binding.viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return navigationIds.size();
            }
            @Override
            public Fragment getItem(int position) {
                return mFragmentsMap.get(navigationIds.get(position));
            }
        });
        binding.viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                binding.navigation.setSelectedItemId(navigationIds.get(position));
            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        binding.viewPager.setCurrentItem(navigationIds.indexOf(menuItem.getItemId()));
        return true;
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragmentsMap.clear();
        navigationIds.clear();
    }

}
