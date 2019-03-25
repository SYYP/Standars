package com.smartwasser.yunzhishui.datatable;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rmondjone.locktableview.DisplayUtil;
import com.rmondjone.locktableview.LockTableView;
import com.rmondjone.xrecyclerview.ProgressStyle;
import com.rmondjone.xrecyclerview.XRecyclerView;
import com.smartwasser.yunzhishui.Activity.BaseActivity;
import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.adapter.alarm.Tadapter;
import com.smartwasser.yunzhishui.alarmbean.ContentBean;
import com.smartwasser.yunzhishui.bean.BuildingResponse;
import com.smartwasser.yunzhishui.bean.BusinessUnitResponse;
import com.smartwasser.yunzhishui.bean.QuotaResponse;
import com.smartwasser.yunzhishui.bean.RBResponse;
import com.smartwasser.yunzhishui.bean.ReimburseListResponse;
import com.smartwasser.yunzhishui.bean.RundataResponse;
import com.smartwasser.yunzhishui.net.HttpLoader;
import com.smartwasser.yunzhishui.record.RunDataActivity;
import com.smartwasser.yunzhishui.rmonactivity.RunDataReslutActivity;
import com.smartwasser.yunzhishui.utils.ConstantsYunZhiShui;
import com.smartwasser.yunzhishui.utils.PopupWindowUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * Created by 15810 on 2019/2/22.
 */

public class WringTableActivity extends BaseActivity implements HttpLoader.ResponseListener, View.OnClickListener {

    private Toolbar toolbar;
    private ImageButton button_menu;
    private TextView tv_toolbar;
    private LinearLayout mContentView;
    private EditText mEdUnit, mEdzb;
    private EditText ed_goujian;
    private BusinessUnitResponse mBusinessUnit;
    private ListView minitListView5, minitListView6, minitListView7;
    private BuildingResponse mBuilding;
    private  Button mRunDataBtn;
    private String businessCode = "";
    private String indeCode = "";
    private  String buildCode="";
    private QuotaResponse mQuota;
    private MyBuildingAdapter mMyBuildingAdapter;//构建物adapter;
    private  MyBusinesAdapter mMyBusinesAdapter;//单位 adapter;
    private MyQuotaAdapter mMyQuotaAdapter;//指标adatper

    @Override
    protected int initContentView() {
        return R.layout.activity_table;
    }

    @Override
    protected void initView() {
        button_menu = (ImageButton) findViewById(R.id.button_menu);
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mEdUnit = (EditText) findViewById(R.id.ed_unit);
        ed_goujian = (EditText) findViewById(R.id.ed_goujian);
        mEdzb = (EditText) findViewById(R.id.ed_yibiao);
        button_menu.setVisibility(View.VISIBLE);
        mRunDataBtn = (Button) findViewById(R.id.run_data_btn);
        button_menu.setBackgroundResource(R.drawable.fanhu);
        toolbar.setTitle("");
        tv_toolbar.setText("实时报警信息");
        setSupportActionBar(toolbar);
        mEdUnit.setOnClickListener(this);
        ed_goujian.setOnClickListener(this);
        mEdzb.setOnClickListener(this);
        mRunDataBtn.setOnClickListener(this);
        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mContentView = (LinearLayout) findViewById(R.id.contentViews);
        initDisplayOpinion();
        //网络去请求
        initAdapter();
    }

    private void initAdapter() {
        //构造假数据
        ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("序号");
        titleList.add("名称");
        titleList.add("报警时间");
        titleList.add("报警类型");
        titleList.add("报警内容");
        mTableDatas.add(titleList);


        ArrayList<ContentBean> mRowDatas = new ArrayList<ContentBean>();
        for (int i = 0; i < 20; i++) {
            ContentBean bean2 = new ContentBean();
            bean2.setT1(i + "");
            bean2.setT2("东方广场");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            String t = format.format(new Date());
            bean2.setT3(t);
            bean2.setT4("通讯报警");
            bean2.setT5("通讯报警");
            mRowDatas.add(bean2);
        }


        for (int i = 0; i < mRowDatas.size(); i++) {
            ArrayList<String> fieldList = new ArrayList<>();
            fieldList.add(mRowDatas.get(i).getT1());
            fieldList.add(mRowDatas.get(i).getT2());
            fieldList.add(mRowDatas.get(i).getT3());
            fieldList.add(mRowDatas.get(i).getT4());
            fieldList.add(mRowDatas.get(i).getT5());
            mTableDatas.add(fieldList);
        }

        final LockTableView mLockTableView = new LockTableView(this, mContentView, mTableDatas);
        Log.e("表格加载开始", "当前线程：" + Thread.currentThread());
        mLockTableView.setLockFristColumn(false) //是否锁定第一列
                .setLockFristRow(true) //是否锁定第一行
                .setMaxColumnWidth(100) //列最大宽度
                .setMinColumnWidth(60) //列最小宽度
//                .setColumnWidth(1,30) //设置指定列文本宽度
//                .setColumnWidth(0,20) //设置指定列文本宽度
                .setColumnWidth(2, 150)
                .setColumnWidth(0, 25)
                .setMinRowHeight(5)//行最小高度
                .setMaxRowHeight(3)//行最大高度
                .setTextViewSize(10) //单元格字体大小
                .setFristRowBackGroudColor(R.color.table_head)//表头背景色
                .setTableHeadTextColor(R.color.beijin)//表头字体颜色
                .setTableContentTextColor(R.color.border_color)//单元格字体颜色
                .setCellPadding(15)//设置单元格内边距(dp)
                .setNullableString("N/A") //空值替换值
                .setTableViewListener(new LockTableView.OnTableViewListener() {
                    @Override
                    public void onTableViewScrollChange(int x, int y) {
//                        Log.e("滚动值","["+x+"]"+"["+y+"]");
                    }
                })//设置横向滚动回调监听
                .setTableViewRangeListener(new LockTableView.OnTableViewRangeListener() {
                    @Override
                    public void onLeft(HorizontalScrollView view) {
                        Log.e("滚动边界", "滚动到最左边");
                    }

                    @Override
                    public void onRight(HorizontalScrollView view) {
                        Log.e("滚动边界", "滚动到最右边");
                    }
                })//设置横向滚动边界监听
                .setOnLoadingListener(new LockTableView.OnLoadingListener() {
                    @Override
                    public void onRefresh(final XRecyclerView mXRecyclerView, final ArrayList<ArrayList<String>> mTableDatas) {
                        Log.e("onRefresh", Thread.currentThread().toString());
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
////                                Log.e("现有表格数据", mTableDatas.toString());
//                                //构造假数据
//                                ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
//                                ArrayList<String> mfristData = new ArrayList<String>();
//                                mfristData.add("标题");
//                                for (int i = 0; i < 10; i++) {
//                                    mfristData.add("标题" + i);
//                                }
//                                mTableDatas.add(mfristData);
//                                for (int i = 0; i < 20; i++) {
//                                    ArrayList<String> mRowDatas = new ArrayList<String>();
//                                    mRowDatas.add("标题" + i);
//                                    for (int j = 0; j < 10; j++) {
//                                        mRowDatas.add("数据" + j);
//                                    }
//                                    mTableDatas.add(mRowDatas);
//                                }
//                                mLockTableView.setTableDatas(mTableDatas);
//                                mXRecyclerView.refreshComplete();
//                            }
//                        }, 1000);
                    }

                    @Override
                    public void onLoadMore(final XRecyclerView mXRecyclerView, final ArrayList<ArrayList<String>> mTableDatas) {
                        Log.e("onLoadMore", Thread.currentThread().toString());
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (mTableDatas.size() <= 60) {
//                                    for (int i = 0; i < 10; i++) {
//                                        ArrayList<String> mRowDatas = new ArrayList<String>();
//                                        mRowDatas.add("标题" + (mTableDatas.size() - 1));
//                                        for (int j = 0; j < 10; j++) {
//                                            mRowDatas.add("数据" + j);
//                                        }
//                                        mTableDatas.add(mRowDatas);
//                                    }
//                                    mLockTableView.setTableDatas(mTableDatas);
//                                } else {
//                                    mXRecyclerView.setNoMore(true);
//                                }
//                                mXRecyclerView.loadMoreComplete();
//                            }
//                        }, 1000);
                    }
                })
                .setOnItemClickListenter(new LockTableView.OnItemClickListenter() {
                    @Override
                    public void onItemClick(View item, int position) {
                        Log.e("点击事件", position + "");
                    }
                })
                .setOnItemLongClickListenter(new LockTableView.OnItemLongClickListenter() {
                    @Override
                    public void onItemLongClick(View item, int position) {
                        Log.e("长按事件", position + "");
                    }
                })
                .setOnItemSeletor(R.color.dashline_color)//设置Item被选中颜色
                .show(); //显示表格,此方法必须调用
//        mLockTableView.getTableScrollView().setPullRefreshEnabled(true);
//        mLockTableView.getTableScrollView().setLoadingMoreEnabled(true);
        mLockTableView.getTableScrollView().setRefreshProgressStyle(ProgressStyle.SquareSpin);
        //属性值获取
        Log.e("每列最大宽度(dp)", mLockTableView.getColumnMaxWidths().toString());
        Log.e("每行最大高度(dp)", mLockTableView.getRowMaxHeights().toString());
        Log.e("表格所有的滚动视图", mLockTableView.getScrollViews().toString());
        Log.e("表格头部固定视图(锁列)", mLockTableView.getLockHeadView().toString());
        Log.e("表格头部固定视图(不锁列)", mLockTableView.getUnLockHeadView().toString());
    }

    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        /**单位*/
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCBUSINESS
                && response instanceof BusinessUnitResponse) {
            mBusinessUnit = (BusinessUnitResponse) response;
            if ("00000".equals(mBusinessUnit.getErrorCode())) {
                mMyBusinesAdapter = new MyBusinesAdapter();
                minitListView5.setAdapter(mMyBusinesAdapter);
                minitListView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mEdUnit.setText(mBusinessUnit.getData().getComboboxList().get(position).getText());
                        businessCode= mBusinessUnit.getData().getComboboxList().get(position).getId();
                        PopupWindowUtils.closePopupWindow();
                    }
                });
            }
        }
        /**构建物*/
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCBUILDING
                && response instanceof BuildingResponse) {
            mBuilding = (BuildingResponse) response;
            if ("00000".equals(mBuilding.getErrorCode())) {
                mMyBuildingAdapter = new MyBuildingAdapter();
                minitListView6.setAdapter(mMyBuildingAdapter);
                minitListView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ed_goujian.setText(mBuilding.getData().getComboboxList().get(position).getText());
                        buildCode = mBuilding.getData().getComboboxList().get(position).getId();
                        PopupWindowUtils.closePopupWindow();
                    }
                });

            }
        }
        /**指标**/
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCQUOTA
                && response instanceof QuotaResponse) {
            mQuota = (QuotaResponse) response;
            if ("00000".equals(mQuota.getErrorCode())) {
                mMyQuotaAdapter = new MyQuotaAdapter();
                minitListView7.setAdapter(mMyQuotaAdapter);
                minitListView7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mEdzb.setText(mQuota.getData().getComboboxList().get(position).getText());
                        indeCode = mQuota.getData().getComboboxList().get(position).getId();
                        PopupWindowUtils.closePopupWindow();
                    }
                });

            }
        }
          //查询
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_BJQUERY
                && response instanceof RundataResponse) {
            RundataResponse mRunData = (RundataResponse) response;
            if ("00000".equals(mRunData.getErrorCode())) {
                /**跳转到指定页面,并传值*/
                EventBus.getDefault().postSticky(mRunData);
                Intent intent = new Intent(WringTableActivity.this, RunDataReslutActivity.class);
                startActivity(intent);
            }
            dismissProgressDialog();
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ed_unit:
                /**单位*/
                minitListView5=initListView5();

                break;
            case R.id.ed_goujian:
                /**构筑物*/
                String s = mEdUnit.getText().toString();
                if ("".equals(s) || "null".equals(s) || s == null) {
                    Toast.makeText(WringTableActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                minitListView6 = initListView6();
                PopupWindowUtils.showPopupWindow(minitListView6, ed_goujian);
                break;
            case R.id.ed_yibiao:
                /**指标*/
                String s2 = mEdUnit.getText().toString();
                if ("".equals(s2) || "null".equals(s2) || s2 == null) {
                    Toast.makeText(WringTableActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                String s3 = ed_goujian.getText().toString();
                if ("".equals(s3) || "null".equals(s3) || s3 == null) {
                    Toast.makeText(WringTableActivity.this, "请选择构筑物", Toast.LENGTH_SHORT).show();
                    return;
                }
                minitListView7 = initListView7();
                PopupWindowUtils.showPopupWindow(minitListView7, mEdzb);
                break;
            case R.id.run_data_btn:

                String dw=mEdUnit.getText().toString();
                if("".equals(dw)||dw==null||"null".equals(dw)){
                    Toast.makeText(WringTableActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                String gzw=ed_goujian.getText().toString();
                if("".equals(gzw)||gzw==null||"null".equals(gzw)){
                    Toast.makeText(WringTableActivity.this, "请选择构筑物", Toast.LENGTH_SHORT).show();
                    return;
                }
                String sb=mEdzb.getText().toString();
                if("".equals(sb)||sb==null||"null".equals(sb)){
                    Toast.makeText(WringTableActivity.this, "请选择指标", Toast.LENGTH_SHORT).show();
                    return;
                }
                //网络去请求
                HashMap<String, Object> prams = new HashMap<>();
                prams.put("businessCode", businessCode);
                prams.put("buildCode", buildCode);
                prams.put("indeCode", indeCode);
        HttpLoader.get(ConstantsYunZhiShui.BJCX, prams,
                ReimburseListResponse.class, ConstantsYunZhiShui.REQUEST_CODE_BJQUERY, WringTableActivity.this, false).setTag(this);
                break;
        }
    }

    private ListView initListView5(){
        ListView mListViews = new ListView(this);
        mListViews.setDividerHeight(0);
        mListViews.setBackgroundResource(R.drawable.listview_background);
        // 去掉右侧垂直滑动条
        mListViews.setVerticalScrollBarEnabled(false);
        /**发起网络请求*/
        HashMap<String, Object> prams = new HashMap<>();
        HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCBUSINESS, prams,
                BusinessUnitResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCBUSINESS, this, false).setTag(this);
        PopupWindowUtils.showPopupWindow( minitListView5,mEdUnit);
        return mListViews;
    }
    private ListView initListView6() {
        ListView mListViews = new ListView(this);
        mListViews.setDividerHeight(0);
        mListViews.setBackgroundResource(R.drawable.listview_background);
        // 去掉右侧垂直滑动条
        mListViews.setVerticalScrollBarEnabled(false);
        /**发起网络请求*/
        HashMap<String, Object> prams = new HashMap<>();
        prams.put("businessCode", businessCode);
        HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCBUILDING, prams,
                BuildingResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCBUILDING, this, false).setTag(this);
        return mListViews;
    }
    /*初始化listView*/

    private ListView initListView7() {
        ListView mListViews = new ListView(this);
        mListViews.setDividerHeight(0);
        mListViews.setBackgroundResource(R.drawable.listview_background);
        // 去掉右侧垂直滑动条
        mListViews.setVerticalScrollBarEnabled(false);
        /**发起网络请求*/
        HashMap<String, Object> prams = new HashMap<>();
        prams.put("businessCode", businessCode);
        prams.put("buildCode", buildCode);
        prams.put("dataType2", "A1");
        HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCQUOTA, prams,
                QuotaResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCQUOTA, this, false).setTag(this);
        return mListViews;
    }

    /* 构筑物适配器*/
    class MyBuildingAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mBuilding.getData().getComboboxList().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(WringTableActivity.this, R.layout.item_search, null);
                holder.v_listview_item_number = (TextView) convertView.findViewById(R.id.tv_listview_item_number);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position == 0) {
                holder.v_listview_item_number.setTextColor(Color.GRAY);
            }
            holder.v_listview_item_number.setText(mBuilding.getData().getComboboxList().get(position).getText());
            return convertView;
        }
    }

    class ViewHolder {
        TextView v_listview_item_number;
    }
    //指标适配器
    class MyQuotaAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mQuota.getData().getComboboxList().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(WringTableActivity.this, R.layout.item_search, null);
                holder.v_listview_item_number = (TextView) convertView.findViewById(R.id.tv_listview_item_number);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position == 0) {
                holder.v_listview_item_number.setTextColor(Color.GRAY);
            }
            holder.v_listview_item_number.setText(mQuota.getData().getComboboxList().get(position).getText());
            return convertView;
        }
    }
    //单位编码适配器
    class MyBusinesAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mBusinessUnit.getData().getComboboxList().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(WringTableActivity.this, R.layout.item_search, null);
                holder.v_listview_item_number = (TextView) convertView.findViewById(R.id.tv_listview_item_number);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position == 0) {
                holder.v_listview_item_number.setTextColor(Color.GRAY);
            }
            holder.v_listview_item_number.setText(mBusinessUnit.getData().getComboboxList().get(position).getText());
            return convertView;
        }
    }
}
