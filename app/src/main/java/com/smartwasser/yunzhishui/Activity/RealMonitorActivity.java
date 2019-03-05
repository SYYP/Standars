package com.smartwasser.yunzhishui.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.adapter.alarm.Tadapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15810 on 2019/2/20.
 *   监测列表
 */

public class RealMonitorActivity extends BaseActivity {
    private ListView mListView;
    private List<String> mlist;
    private Toolbar toolbar;
    private ImageButton mButtonMenu;
    private TextView mTvToolbar;

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };


    @Override
    protected int initContentView() {
        return R.layout.avtivity_alarm;
    }

    @Override
    protected void initView() {
        mButtonMenu = (ImageButton) findViewById(R.id.button_menu);
        mTvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mListView = findViewById(R.id.wraing_list);
        mButtonMenu.setVisibility(View.VISIBLE);
        mButtonMenu.setBackgroundResource(R.drawable.fanhu);
        toolbar.setTitle("");
        mTvToolbar.setText(getResources().getString(R.string.jclb));
        setSupportActionBar(toolbar);
        mButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        getVersionCode();
    }

    private void getVersionCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, needPermissions[2]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                //获取权限列表
                List<String> needRequestPermissonList = findDeniedPermissions(needPermissions);
                if ( null!= needRequestPermissonList && needRequestPermissonList.size() > 0) {
                    //list.toarray将集合转化为数组
                    ActivityCompat.requestPermissions(this,
                            needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]),
                            12);
                }
            }
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        //for (循环变量类型 循环变量名称 : 要被遍历的对象)
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }
    @Override
    protected void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(RealMonitorActivity.this, BzjcActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(RealMonitorActivity.this, JcejcActivity.class);
                        startActivity(intent2);
                        break;
                  default:
                      break;

                }
            }
        });

        mButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        mlist = new ArrayList<>();
        mlist.add("泵站实时监测");
        mlist.add("监测点实时监测");
        Tadapter tadapter = new Tadapter(this, mlist);
        mListView.setAdapter(tadapter);






    }


}
