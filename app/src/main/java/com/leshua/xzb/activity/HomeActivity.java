package com.leshua.xzb.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.leshua.xzb.R;
import com.leshua.xzb.activity.base.BaseActivity;
import com.leshua.xzb.fragment.ChatFragment;
import com.leshua.xzb.fragment.FindFragment;
import com.leshua.xzb.views.NoScrollViewPager;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hch on 2017/3/10.
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.fl_bottom)
    public FrameLayout flBottom;
    @BindView(R.id.rl_add_comment)
    public AutoRelativeLayout rlAddComment;
    @BindView(R.id.et_comment)
    public EditText etComment;
    private NoScrollViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private FragmentManager fm;
    private FindFragment findFragment;
    private ChatFragment chatFragment;
    private MainFragment mainFragment;
    private DynamicFragment dynamicFragment;
    private MineFragment mineFragment;
    private TextView findTV;
    private TextView chatTV;
    private TextView dynamicTV;
    private TextView mineTV;
    // textview for unread message count
    private TextView unreadLabel;
    private int currentTabIndex = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initWidget();
        initViewPager();
    }

    private void initViewPager() {
        mViewPager= (NoScrollViewPager) findViewById(R.id.nsvp_home);
        mViewPager.setOffscreenPageLimit(4);
        initViewPagerData();
    }


    private void initWidget() {
        findViewById(R.id.fl_home_find).setOnClickListener(this);
        findViewById(R.id.fl_home_chat).setOnClickListener(this);
        findViewById(R.id.fl_home_main).setOnClickListener(this);
        findViewById(R.id.fl_home_dynamic).setOnClickListener(this);
        findViewById(R.id.fl_home_mine).setOnClickListener(this);
        findTV = (TextView) findViewById(R.id.tv_home_find);
        chatTV = (TextView) findViewById(R.id.tv_home_chat);
        dynamicTV = (TextView) findViewById(R.id.tv_home_dynamic);
        mineTV = (TextView) findViewById(R.id.tv_home_mine);
        unreadLabel = (TextView) findViewById(R.id.unread_msg_number);
    }

    private void initViewPagerData() {
        fm=getSupportFragmentManager();
        mFragments=new ArrayList<>();
        findFragment=new FindFragment();
        chatFragment=new ChatFragment();
    }
    @Override
    public void onClick(View v) {

    }
}
