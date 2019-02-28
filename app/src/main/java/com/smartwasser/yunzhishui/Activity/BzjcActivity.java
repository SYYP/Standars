package com.smartwasser.yunzhishui.Activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.fragment.BzjcBgFragment;
import com.smartwasser.yunzhishui.fragment.BzjcDtFragment;

/**
 * Name: BzjcActivity
 * Author: Administrator
 * Comment: //TODO
 * Date: 2019-02-28 09:33
 */
public class BzjcActivity extends AppCompatActivity implements View.OnClickListener {
    private Fragment currentf;
    private ImageButton mButtonFan;
    private TextView mTvToolbarZhu;
    /**
     * 泵站实时监测hgghgh
     */
    private TextView mTvToolbarCentext;
    private Toolbar mToolbar2;
    /**
     * 切换样式
     */
    private TextView mTextSerc;
    private FrameLayout mFramLayout;
    private BzjcBgFragment mBzjcBgFragment;
    private BzjcDtFragment mBzjcDtFragment;
    boolean mBoolean = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bzjc);
        initView();
        addFragments(new BzjcDtFragment());
    }


    private void addFragments(Fragment f) {
        // 第一步：得到fragment管理类
        FragmentManager manager = getSupportFragmentManager();
        // 第二步：开启一个事务
        FragmentTransaction transaction = manager.beginTransaction();

        if (currentf != null) {
            //每次把前一个fragment给隐藏了
            transaction.hide(currentf);
        }
        //isAdded:判断当前的fragment对象是否被加载过
        if (!f.isAdded()) {
            // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
            transaction.add(R.id.fram_layout, f);
        }
        //显示当前的fragment
        transaction.show(f);
        // 第四步：提交
        transaction.commit();
        currentf = f;
    }

    private void initView() {
        mButtonFan = (ImageButton) findViewById(R.id.button_fan);
        mTvToolbarZhu = (TextView) findViewById(R.id.tv_toolbar_zhu);
        mTvToolbarCentext = (TextView) findViewById(R.id.tv_toolbar_centext);
        mToolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        mTextSerc = (TextView) findViewById(R.id.text_serc);
        mFramLayout = (FrameLayout) findViewById(R.id.fram_layout);
        mTextSerc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_serc://切换类型
                if (mBoolean) {
                    mBoolean = false;
                    //加载表格样式
                    if (mBzjcBgFragment == null) {
                        mBzjcBgFragment = new BzjcBgFragment();
                    }
                    addFragments(mBzjcBgFragment);
                } else {
                    mBoolean = true;
                      //加载地图样式
                    if (mBzjcDtFragment == null) {
                        mBzjcDtFragment = new BzjcDtFragment();
                    }
                    addFragments(mBzjcDtFragment);
                }
                break;
            default:
                break;
        }
    }
}
