package com.smartwasser.yunzhishui.statistics;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.rmondjone.locktableview.DisplayUtil;
import com.rmondjone.locktableview.LockTableView;
import com.rmondjone.xrecyclerview.ProgressStyle;
import com.rmondjone.xrecyclerview.XRecyclerView;
import com.smartwasser.yunzhishui.Activity.BaseActivity;
import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.alarmbean.CountBean;
import com.smartwasser.yunzhishui.alarmbean.CountBean;
import com.smartwasser.yunzhishui.bean.BusinessUnitResponse;
import com.smartwasser.yunzhishui.bean.RBResponse;
import com.smartwasser.yunzhishui.net.HttpLoader;
import com.smartwasser.yunzhishui.productionactivity.ContractActivity;
import com.smartwasser.yunzhishui.utils.ConstantsYunZhiShui;
import com.smartwasser.yunzhishui.utils.DialogTimeUtils;
import com.smartwasser.yunzhishui.utils.ListViewUtils;
import com.smartwasser.yunzhishui.utils.PopListViewUtils;
import com.smartwasser.yunzhishui.utils.PopupWindowUtils;
import com.smartwasser.yunzhishui.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 15810 on 2019/2/26.
 */

public class ContInflowActivity extends BaseActivity implements View.OnClickListener, HttpLoader.ResponseListener{
    private List<String> mlist;
    private ListView minitListView;
    private EditText mTvShuiChang;
    private LinearLayout contentView;
    private Toolbar toolbar;
    private ImageButton button_menu;
    private TextView tv_toolbar;
    private TextView mRightTitle;
    private WebView mWdebView;


    private ListView minitListView5;
    private TimeUtils time = new TimeUtils();
    private MyBusinesAdapter myBusinesAdapter;
    private String code = "";
    private EditText tv_shui_chang_edit;
    private EditText ed_count_strattime;
    private DialogTimeUtils dialog=new DialogTimeUtils(this);
    private PopListViewUtils plu=new PopListViewUtils(this);
    private BusinessUnitResponse mBusinessUnit;
    @Override
    protected int initContentView() {
        return R.layout.activity_count_inflow;
    }

    @Override
    protected void initView() {
        mTvShuiChang =   findViewById(R.id.tv_shui_chang_edit);
        contentView = findViewById(R.id.contentView);
        button_menu= (ImageButton) findViewById(R.id.button_menu);
        mRightTitle = (TextView) findViewById(R.id.right_title);
        tv_toolbar= (TextView) findViewById(R.id.tv_toolbar);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        mWdebView= (WebView) findViewById(R.id.chartshow_wb);
        mWdebView= (WebView) findViewById(R.id.chartshow_wb);
        //进行webwiev的一堆设置
        //开启本地文件读取（默认为true，不设置也可以）
        mWdebView.getSettings().setAllowFileAccess(true);
        //开启脚本支持
        mWdebView.getSettings().setJavaScriptEnabled(true);
        mWdebView.loadUrl("file:///android_asset/myechart.html");


        tv_shui_chang_edit = findViewById(R.id.tv_shui_chang_edit);
        ed_count_strattime = findViewById(R.id.ed_count_strattime);
        tv_shui_chang_edit.setOnClickListener(this);
        ed_count_strattime.setOnClickListener(this);

        button_menu.setVisibility(View.VISIBLE);
        button_menu.setBackgroundResource(R.drawable.fanhu);
        toolbar.setTitle("");
        tv_toolbar.setText("厂进水量指标统计");
        setSupportActionBar(toolbar);
        mRightTitle.setText("报表");




        mWdebView.loadUrl("javascript:doCreatChart('bar',[89,78,77,44,66,83,56,26,97,56,12,48]);");


        mRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btnText = mRightTitle.getText().toString();
                if ("报表".equals(btnText)){
                    mWdebView.loadUrl("javascript:doCreatChart('line',[89,78,77,44,66,83,56,26,97,56,12,48]);");
                    contentView.setVisibility(View.GONE);
                    mWdebView.setVisibility(View.VISIBLE);
                    mRightTitle.setText("表格");
                }else {
                    contentView.setVisibility(View.VISIBLE);
                    mWdebView.setVisibility(View.GONE);
                    mRightTitle.setText("报表");
                }

            }
        });

    }

    @Override
    protected void initListener() {
        initDisplayOpinion();

        initAdapter();

        mTvShuiChang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListViewUtils utils=new ListViewUtils(getApplicationContext());
                minitListView=utils.initListView(mlist);
                PopupWindowUtils.showPopupWindow(minitListView,mTvShuiChang);
                minitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTvShuiChang.setText(mlist.get(position));
                        PopupWindowUtils.closePopupWindow();
                    }
                });
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
        mlist=new ArrayList<>();
        mlist.add("测试数据");
        mlist.add("测试数据");
        mlist.add("测试数据");
        mlist.add("测试数据");
        mlist.add("测试数据");
        mlist.add("测试数据");

    }


    private void initAdapter() {
        //构造假数据
        ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("日期");
        titleList.add("A");
        titleList.add("B");
        titleList.add("C");
        titleList.add("D");
        titleList.add("E");
        titleList.add("F");
        titleList.add("合计");
        titleList.add("总水量");
        titleList.add("处理水断");
        mTableDatas.add(titleList);




        ArrayList<CountBean> mRowDatas = new ArrayList<CountBean>();
        for (int i=0;i<20;i++){
            int num = (int) ((Math.random() * 9 + 1) * 100000);
            CountBean bean2 = new CountBean();
            bean2.setT0("2013-06-11");
            bean2.setT2(num+"");
            bean2.setT3(num+"12"+i);
            bean2.setT4(num+"0"+i);
            bean2.setT5(num+"2"+i);
            bean2.setT6(num+"8"+i);
            bean2.setT1(num+"8"+i);
            bean2.setT7(num+"8"+i);
            bean2.setT8(num+"8"+i);
            bean2.setT9(num+"8"+i);
            mRowDatas.add(bean2);
        }


        for (int i=0;i<mRowDatas.size();i++){
            ArrayList<String> fieldList = new ArrayList<>();
            fieldList.add(mRowDatas.get(i).getT0());
            fieldList.add(mRowDatas.get(i).getT1());
            fieldList.add(mRowDatas.get(i).getT2());
            fieldList.add(mRowDatas.get(i).getT3());
            fieldList.add(mRowDatas.get(i).getT4());
            fieldList.add(mRowDatas.get(i).getT5());
            fieldList.add(mRowDatas.get(i).getT6());
            fieldList.add(mRowDatas.get(i).getT7());
            fieldList.add(mRowDatas.get(i).getT8());
            fieldList.add(mRowDatas.get(i).getT9());
            mTableDatas.add(fieldList);
        }

        final LockTableView mLockTableView = new LockTableView(this, contentView, mTableDatas);
        Log.e("表格加载开始", "当前线程：" + Thread.currentThread());
        mLockTableView.setLockFristColumn(false) //是否锁定第一列
                .setLockFristRow(true) //是否锁定第一行
                .setMaxColumnWidth(100) //列最大宽度
                .setMinColumnWidth(60) //列最小宽度
//                .setColumnWidth(1,30) //设置指定列文本宽度
//                .setColumnWidth(0,20) //设置指定列文本宽度
                .setColumnWidth(1, 100)
                .setColumnWidth(0, 50)
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
                        Log.e("滚动边界","滚动到最左边");
                    }

                    @Override
                    public void onRight(HorizontalScrollView view) {
                        Log.e("滚动边界","滚动到最右边");
                    }
                })//设置横向滚动边界监听
                .setOnLoadingListener(new LockTableView.OnLoadingListener() {
                    @Override
                    public void onRefresh(final XRecyclerView mXRecyclerView, final ArrayList<ArrayList<String>> mTableDatas) {
                        Log.e("onRefresh",Thread.currentThread().toString());
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
                        Log.e("onLoadMore",Thread.currentThread().toString());
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
                        Log.e("点击事件",position+"");
                    }
                })
                .setOnItemLongClickListenter(new LockTableView.OnItemLongClickListenter() {
                    @Override
                    public void onItemLongClick(View item, int position) {
                        Log.e("长按事件",position+"");
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
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shui_chang_edit:
                minitListView5 = initListView5();
                break;
            case R.id.ed_count_strattime:
                /**开始时间*/
                dialog.show(ed_count_strattime);
                dialog.showTime();
                break;
        }
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCBUSINESS
                && response instanceof BusinessUnitResponse) {
            mBusinessUnit = (BusinessUnitResponse) response;
            if ("00000".equals(mBusinessUnit.getErrorCode())) {
                myBusinesAdapter = new MyBusinesAdapter();
                minitListView5.setAdapter(myBusinesAdapter);
                minitListView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tv_shui_chang_edit.setText(mBusinessUnit.getData().getComboboxList().get(position).getText());
                        code = mBusinessUnit.getData().getComboboxList().get(position).getId();
                        PopupWindowUtils.closePopupWindow();
                    }
                });
            }
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }


    /*条目的适配器*/
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
                convertView = View.inflate(ContInflowActivity.this, R.layout.item_search, null);
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

    class ViewHolder {
        TextView v_listview_item_number;
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
}
