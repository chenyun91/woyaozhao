package com.leshua.xzb.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewManager;

import com.leshua.xzb.R;
import com.leshua.xzb.activity.base.BaseActivity;
import com.leshua.xzb.adapter.GuidePagerAdapter;

/**
 * Created by hch on 2017/3/10.
 */

public class GuideActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ViewPager vp= (ViewPager) findViewById(R.id.vp_guide);
        PagerAdapter pAdapter=new GuidePagerAdapter(GuideActivity.this);
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(pAdapter);

    }

}
