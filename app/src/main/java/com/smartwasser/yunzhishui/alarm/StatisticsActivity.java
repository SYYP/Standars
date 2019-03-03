package com.smartwasser.yunzhishui.alarm;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.smartwasser.yunzhishui.Activity.BaseActivity;
import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.adapter.alarm.Tadapter;
import com.smartwasser.yunzhishui.statistics.BuildCountActivity;
import com.smartwasser.yunzhishui.statistics.ContInflowActivity;
import com.smartwasser.yunzhishui.statistics.EcectrMothActivity;
import com.smartwasser.yunzhishui.statistics.ElectricCountActivity;
import com.smartwasser.yunzhishui.statistics.InterestCountActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15810 on 2019/2/22.
 */

public class StatisticsActivity extends BaseActivity {
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
        toolbar.setTitle("");
        tv_toolbar.setText("汇总统计");
        setSupportActionBar(toolbar);
        button_menu.setBackgroundResource(R.drawable.fanhu);
    }


    @Override
    protected void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(StatisticsActivity.this,ContInflowActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(StatisticsActivity.this,InterestCountActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(StatisticsActivity.this,ElectricCountActivity.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4= new Intent(StatisticsActivity.this,EcectrMothActivity.class);
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent(StatisticsActivity.this,BuildCountActivity.class);
                        startActivity(intent5);
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
        mlist.add("厂进水量指标统计");
        mlist.add("厂设备利用率统计");
        mlist.add("厂用电量年统计");
        mlist.add("厂用电量月统计");
        mlist.add("构筑物用电量月统计");
        Tadapter tadapter = new Tadapter(this, mlist);
        mListView.setAdapter(tadapter);
    }
}
