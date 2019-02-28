package com.smartwasser.yunzhishui.Activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.adapter.alarm.Tadapter;
import com.smartwasser.yunzhishui.alarm.AlarmQueryActivity;
import com.smartwasser.yunzhishui.datatable.WringTableActivity;

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
    private ImageButton button_menu;
    private TextView tv_toolbar;


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
        tv_toolbar.setText("监测列表");
        setSupportActionBar(toolbar);
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
                        Intent intent2 = new Intent(RealMonitorActivity.this, AlarmQueryActivity.class);
                        startActivity(intent2);
                        break;
                  default:
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
        mlist = new ArrayList<>();
        mlist.add("泵站实时监测");
        mlist.add("监测点实时监测");
        Tadapter tadapter = new Tadapter(this, mlist);
        mListView.setAdapter(tadapter);






    }


}
