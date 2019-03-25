package com.smartwasser.yunzhishui.alarm;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.smartwasser.yunzhishui.Activity.BaseActivity;
import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.adapter.alarm.Tadapter;
import com.smartwasser.yunzhishui.bean.RBResponse;
import com.smartwasser.yunzhishui.bean.RmonMenuResponse;
import com.smartwasser.yunzhishui.datatable.WringTableActivity;
import com.smartwasser.yunzhishui.net.HttpLoader;
import com.smartwasser.yunzhishui.utils.ConstantsYunZhiShui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 15810 on 2019/2/20.
 */

public class AlarmActivity extends BaseActivity implements HttpLoader.ResponseListener{
    private ListView mListView;
    private List<String> mlist;
    private Toolbar toolbar;
    private ImageButton button_menu;
    private TextView tv_toolbar;
    private RmonMenuResponse menuResponse;

    @Override
    protected int initContentView() {
        return R.layout.avtivity_alarm;
    }

    @Override
    protected void initView() {
        button_menu = (ImageButton) findViewById(R.id.button_menu);
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mListView = findViewById(R.id.wraing_list);
        button_menu.setVisibility(View.VISIBLE);
        toolbar.setTitle("");
        button_menu.setBackgroundResource(R.drawable.fanhu);
        tv_toolbar.setText("预警报警");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(AlarmActivity.this, WringTableActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(AlarmActivity.this, AlarmQueryActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;

                }
            }
        });

        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        HashMap<String, Object> prams = new HashMap<>();
        prams.put("id","138372619389084886281");
        HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCMENULIST, prams,
                RmonMenuResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCMENULIST, this).setTag(this);
        mlist = new ArrayList<>();
        mlist.add("实时报警信息");
        mlist.add("历史报警查询");
        mlist.add("重要报警提示");
        mlist.add("紧急报警");







    }


    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCMENULIST
                && response instanceof RmonMenuResponse) {
            menuResponse = (RmonMenuResponse) response;
            List<RmonMenuResponse.DataBean> data = menuResponse.getData();
            Tadapter tadapter = new Tadapter(this, data);
            mListView.setAdapter(tadapter);
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}
