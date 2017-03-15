package org.china.xzb.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;


import org.china.xzb.R;
import org.china.xzb.activity.base.BaseActivity;
import org.china.xzb.adapter.GuidePagerAdapter;

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
