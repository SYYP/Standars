package com.smartwasser.yunzhishui.record;

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
import com.smartwasser.yunzhishui.net.HttpLoader;
import com.smartwasser.yunzhishui.utils.ConstantsYunZhiShui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 15810 on 2019/3/1.
 */

public class HistoryRecordActivity extends BaseActivity implements HttpLoader.ResponseListener{
    private ListView mListView;
    private List<String> mlist;
    private Toolbar toolbar;
    private ImageButton button_menu;
    private TextView tv_toolbar;
    private RmonMenuResponse menuResponse;
    private List<RmonMenuResponse.DataBean> data;

    @Override
    protected int initContentView() {
        return R.layout.activity_history;
    }

    @Override
    protected void initView() {
        button_menu = (ImageButton) findViewById(R.id.button_menu);
        mListView = findViewById(R.id.history_list);
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        button_menu.setVisibility(View.VISIBLE);
        button_menu.setBackgroundResource(R.drawable.fanhu);
        toolbar.setTitle("");
        tv_toolbar.setText("历史查询");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initListener() {
        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            Intent intent = null;
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RmonMenuResponse.DataBean dataBean = data.get(i);

                switch (i) {
                    case 0:
                         intent = new Intent(HistoryRecordActivity.this,RunDataActivity.class);
                        break;
                    case 1:
                         intent = new Intent(HistoryRecordActivity.this,FacilityStateActivity.class);
                        break;
                    case 2:
                         intent = new Intent(HistoryRecordActivity.this,PumpStateActivity.class);
                        break;
                    case 3:
                         intent = new Intent(HistoryRecordActivity.this,WaterCountActivity.class);
                        break;
                    case 4:
                         intent = new Intent(HistoryRecordActivity.this,ElectricityCountActivity.class);
                        break;
                }
                intent.putExtra("title",dataBean);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mlist = new ArrayList<>();
        mlist.add("运行数据查询");
        mlist.add("设备状态查询");
        mlist.add("泵站运行状态查询");
        mlist.add("处理水量查询");
        mlist.add("用电量查询");
//        Tadapter tadapter = new Tadapter(this, mlist);
//        mListView.setAdapter(tadapter);

        HashMap<String, Object> prams = new HashMap<>();
        prams.put("id","138372614509302817214");
        HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCMENULIST, prams,
                RmonMenuResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCMENULIST, this).setTag(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCMENULIST
                && response instanceof RmonMenuResponse) {
            menuResponse = (RmonMenuResponse) response;
            data = menuResponse.getData();
            Tadapter tadapter = new Tadapter(this, data);
            mListView.setAdapter(tadapter);
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}
