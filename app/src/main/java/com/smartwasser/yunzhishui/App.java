package com.smartwasser.yunzhishui;
import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.baidu.mapapi.SDKInitializer;


/**
 * Created by xiongmc on 2015/12/22.
 */
public class App extends Application {
    //测试提交sss
    /**
     * 全局Application，方便调用
     */
    public static App application;
    public SharedPreferences SP;
    public SharedPreferences.Editor EDIT;
// 要申请的权限
    private String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION};
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        SDKInitializer.initialize(this);
        SP = getSharedPreferences("config", MODE_PRIVATE);
        EDIT = SP.edit();
    }
}
