package com.leshua.xzb.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by hch on 2017/3/10.
 */

public class BaseActivity extends FragmentActivity {

    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public BaseActivity(){
        this.context=this;
    }
}
