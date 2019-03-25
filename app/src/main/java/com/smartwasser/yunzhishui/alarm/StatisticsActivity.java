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
import com.smartwasser.yunzhishui.net.HttpLoader;
import com.smartwasser.yunzhishui.statistics.BuildCountActivity;
import com.smartwasser.yunzhishui.statistics.ContInflowActivity;
import com.smartwasser.yunzhishui.statistics.EcectrMothActivity;
import com.smartwasser.yunzhishui.statistics.ElectricCountActivity;
import com.smartwasser.yunzhishui.statistics.InterestCountActivity;
import com.smartwasser.yunzhishui.utils.ConstantsYunZhiShui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 15810 on 2019/2/22.
 */

public class StatisticsActivity extends BaseActivity implements HttpLoader.ResponseListener{
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
                List<RmonMenuResponse.DataBean> data = menuResponse.getData();
                RmonMenuResponse.DataBean dataBean = data.get(i);
                Intent intent = null;
                switch (i) {
                    case 0:
                        intent   = new Intent(StatisticsActivity.this,ContInflowActivity.class);
                        break;
                    case 1:
                        intent = new Intent(StatisticsActivity.this,InterestCountActivity.class);
                        break;
                    case 2:
                        intent = new Intent(StatisticsActivity.this,ElectricCountActivity.class);
                        break;
                    case 3:
                        intent= new Intent(StatisticsActivity.this,EcectrMothActivity.class);
                        break;
                    case 4:
                        intent = new Intent(StatisticsActivity.this,BuildCountActivity.class);

                        break;
                }
                intent.putExtra("title",dataBean);
                startActivity(intent);
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
        HashMap<String, Object> prams = new HashMap<>();
        prams.put("id","138372713004600042063");
        HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCMENULIST, prams,
                RmonMenuResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCMENULIST, this).setTag(this);
//        Tadapter tadapter = new Tadapter(this, mlist);
//        mListView.setAdapter(tadapter);
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
