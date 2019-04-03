package com.smartwasser.yunzhishui.alarm;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
import com.smartwasser.yunzhishui.alarmbean.ContentBean;
import com.smartwasser.yunzhishui.bean.BuildingResponse;
import com.smartwasser.yunzhishui.bean.BusinessUnitResponse;
import com.smartwasser.yunzhishui.bean.QuotaResponse;
import com.smartwasser.yunzhishui.bean.RBResponse;
import com.smartwasser.yunzhishui.bean.RundataResponse;
import com.smartwasser.yunzhishui.net.HttpLoader;
import com.smartwasser.yunzhishui.utils.ConstantsYunZhiShui;
import com.smartwasser.yunzhishui.utils.DialogTimeUtils;
import com.smartwasser.yunzhishui.utils.PopupWindowUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by 15810 on 2019/2/22.
 */

public class AlarmQueryActivity extends BaseActivity implements View.OnClickListener, HttpLoader.ResponseListener {

    private Toolbar toolbar;
    private ImageButton button_menu;
    private TextView tv_toolbar;
    private LinearLayout mContentView;
    private ImageButton mButtonMenu;

    private DialogTimeUtils dialog = new DialogTimeUtils(this);
    private String businessCode = "";
    private String buildCode = "";
    private String indeCode = "";
    /**
     * 名称:
     */
    private TextView mTvName;
    /**
     * 请输入名称
     */
    private EditText mTvEditName;
    /**
     * 报警类型:
     */
    private TextView mTvType;
    /**
     * 全部
     */
    private TextView mTvEdit;

    private EditText mEdMuchtrendStrattime;
    private ImageButton mButMuchtrendStrattime;

    private EditText mEdMuchtrendEndtime;
    private ImageButton mButMuchtrendEndtime;
    private MyQuotaAdapter mMyQuotaAdapter;//指标adatper
    /**
     * 查询
     */
    private TextView mTvQuery;
    private EditText mTvBm, mTvZb;
    private ListView minitListView2, minitListView3, minitListView4,
            minitListView5, minitListView6, minitListView7;
    private BusinessUnitResponse mBusinessUnit;
    private MyBusinesAdapter mMyBusinesAdapter;//单位 adapter;
    private BuildingResponse mBuilding;
    private MyBuildingAdapter mMyBuildingAdapter;//构建物adapter;
    private QuotaResponse mQuota;

    @Override
    protected int initContentView() {
        return R.layout.activity_alarmquery;
    }

    @Override
    protected void initView() {
        button_menu = (ImageButton) findViewById(R.id.button_menu);
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvEditName = findViewById(R.id.tv_edit_name);//单位
        mTvBm = findViewById(R.id.tv_bm);//构筑物
        mTvZb = findViewById(R.id.tv_zb);//指标
        mTvEditName.setOnClickListener(this);
        mTvBm.setOnClickListener(this);
        mTvZb.setOnClickListener(this);
        button_menu.setVisibility(View.VISIBLE);
//        button_menu.setBackground(R.drawable.);
        toolbar.setTitle("");
        tv_toolbar.setText("报警查询");
        setSupportActionBar(toolbar);
        button_menu.setBackgroundResource(R.drawable.fanhu);
        mButtonMenu = (ImageButton) findViewById(R.id.button_menu);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvType = (TextView) findViewById(R.id.tv_type);
        mEdMuchtrendStrattime = (EditText) findViewById(R.id.ed_muchtrend_strattime);
        mButMuchtrendStrattime = (ImageButton) findViewById(R.id.but_muchtrend_strattime);
        mEdMuchtrendEndtime = (EditText) findViewById(R.id.ed_muchtrend_endtime);
        mButMuchtrendEndtime = (ImageButton) findViewById(R.id.but_muchtrend_endtime);
        mTvQuery = (TextView) findViewById(R.id.tv_query);
        mContentView = (LinearLayout) findViewById(R.id.contentView);
        mEdMuchtrendStrattime.setOnClickListener(this);
        mEdMuchtrendEndtime.setOnClickListener(this);
        mTvQuery.setOnClickListener(this);
    }

    @Override
    protected void initListener() {
        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        mContentView = (LinearLayout) findViewById(R.id.contentView);
        initDisplayOpinion();

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
                .setTextViewSize(13) //单元格字体大小
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit_name://单位
//                /**单位*/
                minitListView5 = initListView5();
                PopupWindowUtils.showPopupWindow(minitListView5, mTvEditName);
                /**构筑物*/

                break;
            case R.id.tv_bm://构筑物
                String s = mTvEditName.getText().toString();
                if ("".equals(s) || "null".equals(s) || s == null) {
                    Toast.makeText(AlarmQueryActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }

                minitListView6=initListView6();
                PopupWindowUtils.showPopupWindow( minitListView6,mTvBm);
                break;
            case R.id.tv_zb://指标
                String s2 = mTvEditName.getText().toString();
                if ("".equals(s2) || "null".equals(s2) || s2 == null) {
                    Toast.makeText(AlarmQueryActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                String s3 = mTvBm.getText().toString();
                if ("".equals(s3) || "null".equals(s3) || s3 == null) {
                    Toast.makeText(AlarmQueryActivity.this, "请选择构筑物", Toast.LENGTH_SHORT).show();
                    return;
                }
                minitListView7=initListView7();
                PopupWindowUtils.showPopupWindow(minitListView7, mTvZb);
                break;
            case R.id.ed_muchtrend_strattime://开始时间
                /**开始时间*/
                dialog.show(mEdMuchtrendStrattime);
                dialog.showTime();
                break;
            case R.id.ed_muchtrend_endtime://结束时间
                /**结束时间*/
                dialog.show(mEdMuchtrendEndtime);
                dialog.showTime();
                break;
            case R.id.tv_query:
                /**查询*/

                String dw = mTvEditName.getText().toString();
                if ("".equals(dw) || dw == null || "null".equals(dw)) {
                    Toast.makeText(AlarmQueryActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                String gzw = mTvBm.getText().toString();
                if ("".equals(gzw) || gzw == null || "null".equals(gzw)) {
                    Toast.makeText(AlarmQueryActivity.this, "请选择构筑物", Toast.LENGTH_SHORT).show();
                    return;
                }
                String sb = mTvZb.getText().toString();
                if ("".equals(sb) || sb == null || "null".equals(sb)) {
                    Toast.makeText(AlarmQueryActivity.this, "请选择设备", Toast.LENGTH_SHORT).show();
                    return;
                }
                String start = mEdMuchtrendStrattime.getText().toString();
                String end = mEdMuchtrendEndtime.getText().toString();
                if ("".equals(start) || start == null || "null".equals(start)) {
                    Toast.makeText(AlarmQueryActivity.this, "请选择开始时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap prams=new HashMap();
                prams.put("businessCode",businessCode);
                prams.put("buildCode",buildCode);
                prams.put("indeCode",indeCode);
                prams.put("dataTimeFrom",start);
                prams.put("dataTimeTo",end);
                HttpLoader.get(ConstantsYunZhiShui.HISTORY_BJ_QUERY, prams,
                        RundataResponse.class, ConstantsYunZhiShui.REQUEST_CODE_HISTORY_QUERY, this, false).setTag(this);
                showProgressDialog();
                break;
        }
    }
    private ListView initListView5() {
        ListView mListViews = new ListView(this);
        mListViews.setDividerHeight(0);
        mListViews.setBackgroundResource(R.drawable.listview_background);
        // 去掉右侧垂直滑动条
        mListViews.setVerticalScrollBarEnabled(false);
        /**发起网络请求*/
        HashMap<String, Object> prams = new HashMap<>();
        HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCBUSINESS, prams,
                BusinessUnitResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCBUSINESS, this, false).setTag(this);
        return mListViews;
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
                        mTvEditName.setText(mBusinessUnit.getData().getComboboxList().get(position).getText());
                        businessCode = mBusinessUnit.getData().getComboboxList().get(position).getId();
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
                        mTvBm.setText(mBuilding.getData().getComboboxList().get(position).getText());
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
                        mTvZb.setText(mQuota.getData().getComboboxList().get(position).getText());
                        indeCode = mQuota.getData().getComboboxList().get(position).getId();
                        PopupWindowUtils.closePopupWindow();
                    }
                });

            }
        }if (requestCode==ConstantsYunZhiShui.REQUEST_CODE_HISTORY_QUERY
                && response instanceof RundataResponse){
            RundataResponse rundataResponse = (RundataResponse) response;
            RundataResponse.DataEntity data = rundataResponse.getData();
            if (data==null){
                Toast.makeText(this,"这段时间没有数据",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

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
                convertView = View.inflate(AlarmQueryActivity.this, R.layout.item_search, null);
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
        prams.put("dataType2", "B1");
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
                convertView = View.inflate(AlarmQueryActivity.this, R.layout.item_search, null);
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
                convertView = View.inflate(AlarmQueryActivity.this, R.layout.item_search, null);
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

    class ViewHolder {
        TextView v_listview_item_number;
    }
}
