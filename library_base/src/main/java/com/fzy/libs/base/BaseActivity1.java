package com.fzy.libs.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity1 extends AppCompatActivity {

    protected String TAG;
    protected Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        mContext = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
