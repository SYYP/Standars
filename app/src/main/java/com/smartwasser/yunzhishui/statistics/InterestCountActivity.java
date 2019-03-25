package com.smartwasser.yunzhishui.statistics;

import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rmondjone.locktableview.DisplayUtil;
import com.rmondjone.locktableview.LockTableView;
import com.rmondjone.xrecyclerview.ProgressStyle;
import com.rmondjone.xrecyclerview.XRecyclerView;
import com.smartwasser.yunzhishui.Activity.BaseActivity;
import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.alarmbean.IntersetBean;
import com.smartwasser.yunzhishui.bean.RmonMenuResponse;
import com.smartwasser.yunzhishui.utils.DialogTimeUtils;
import com.smartwasser.yunzhishui.utils.ListViewUtils;
import com.smartwasser.yunzhishui.utils.PopupWindowUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15810 on 2019/2/27.
 */

public class InterestCountActivity extends BaseActivity {
    private List<String> mlist;
    private ListView minitListView;
    private EditText mEInterset;
    private LinearLayout contentView;
    private Toolbar toolbar;
    private ImageButton button_menu;
    private TextView tv_toolbar;
    private WebView mWebView;
    private TextView mRightTitle;
    private LinearLayout mWebLinea;
    private EditText ed_interset_rdit;
    private DialogTimeUtils dialog = new DialogTimeUtils(this);
    @Override
    protected int initContentView() {
        return R.layout.activity_interset_count;
    }

    @Override
    protected void initView() {
        mEInterset = findViewById(R.id.ed_interset);
        contentView = findViewById(R.id.contentView);
        button_menu = (ImageButton) findViewById(R.id.button_menu);
        mRightTitle = (TextView) findViewById(R.id.right_title);
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mWebView = findViewById(R.id.chartshow_wbs);
        ed_interset_rdit = findViewById(R.id.ed_interset_rdit);
        ed_interset_rdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**开始时间*/
                dialog.show(ed_interset_rdit);
                dialog.showTime();
            }
        });


// 设置可以支持缩放
        mWebView.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true);
//扩大比例的缩放
        mWebView.getSettings().setUseWideViewPort(true);
//自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);



        button_menu.setVisibility(View.VISIBLE);
        button_menu.setBackgroundResource(R.drawable.fanhu);
        toolbar.setTitle("");
        RmonMenuResponse.DataBean dataBean = (RmonMenuResponse.DataBean) getIntent().getSerializableExtra("title");
        tv_toolbar.setText(dataBean.getFuncnamech());
        setSupportActionBar(toolbar);
        mRightTitle.setText("报表");
        mWebView.loadUrl("javascript:doCreatChart('bar',[89,78,77,44,66,83,56,26,97,56,12,48]);");


        mRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btnText = mRightTitle.getText().toString();
                if ("报表".equals(btnText)){
                    mWebView.loadUrl("javascript:doCreatChart('bar',[89,78,77,44,66,83,56,26,97,56,12,48]);");
                    contentView.setVisibility(View.GONE);
                    mWebView.setVisibility(View.VISIBLE);
                    mRightTitle.setText("表格");
                }else {
                    contentView.setVisibility(View.VISIBLE);
                    mWebView.setVisibility(View.GONE);
                    mRightTitle.setText("报表");
                }

            }
        });
    }

    @Override
    protected void initListener() {
        initDisplayOpinion();

        initAdapter();

        mEInterset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListViewUtils utils = new ListViewUtils(getApplicationContext());
                minitListView = utils.initListView(mlist);
                PopupWindowUtils.showPopupWindow(minitListView, mEInterset);
                minitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mEInterset.setText(mlist.get(position));
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


        mlist = new ArrayList<>();
        mlist.add("测试数据");
        mlist.add("测试数据");
        mlist.add("测试数据");
        mlist.add("测试数据");
        mlist.add("测试数据");
        mlist.add("测试数据");

        Init();
    }

    private void Init() {

        //进行webwiev的一堆设置
        //开启本地文件读取（默认为true，不设置也可以）
        mWebView.getSettings().setAllowFileAccess(true);
        //开启脚本支持
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/myechart.html");
    }


    private void initAdapter() {
        //构造假数据
        ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("单位名称");
        titleList.add("01");
        titleList.add("02");
        titleList.add("03");
        titleList.add("04");
        titleList.add("05");
        titleList.add("06");
        titleList.add("07");
        titleList.add("08");
        titleList.add("09");
        titleList.add("10");
        titleList.add("11");
        titleList.add("12");
        titleList.add("单位月平均");
        mTableDatas.add(titleList);


        ArrayList<IntersetBean> mRowDatas = new ArrayList<IntersetBean>();
        for (int i = 0; i < 20; i++) {
            int num = (int) ((Math.random() * 9 + 1) * 100);
            IntersetBean bean2 = new IntersetBean();
            bean2.setT1("清河污水厂");
            bean2.setT2(num + "%");
            bean2.setT3(num + "." + i + "%");
            bean2.setT4(num + ".30" + i + "%");
            bean2.setT5(num + ".42" + i + "%");
            bean2.setT6(num + ".98" + i + "%");
            bean2.setT1(num + ".78" + i + "%");
            bean2.setT7(num + ".88" + i + "%");
            bean2.setT8(num + ".53" + i + "%");
            bean2.setT9(num + ".81" + i + "%");
            bean2.setT10(num + ".71" + i + "%");
            bean2.setT11(num + ".23" + i + "%");
            bean2.setT12(num + ".5" + i + "%");
            bean2.setT13(num + ".9" + i + "%");
            bean2.setT14(num + ".12" + i + "%");
            mRowDatas.add(bean2);
        }


        for (int i = 0; i < mRowDatas.size(); i++) {
            ArrayList<String> fieldList = new ArrayList<>();
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
        mLockTableView.setLockFristColumn(true) //是否锁定第一列
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



        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;

        int height=dm.heightPixels;

        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mWebView.getLayoutParams();

        linearParams.height = height;
        linearParams.width = width;

        mWebView.setLayoutParams(linearParams);
    }

}
