package com.smartwasser.yunzhishui.record;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
import com.smartwasser.yunzhishui.alarmbean.CountBean;
import com.smartwasser.yunzhishui.bean.BuildingResponse;
import com.smartwasser.yunzhishui.bean.BusinessUnitResponse;
import com.smartwasser.yunzhishui.bean.EventNormSelect;
import com.smartwasser.yunzhishui.bean.QuotaResponse;
import com.smartwasser.yunzhishui.bean.RBResponse;
import com.smartwasser.yunzhishui.bean.RmonSelectResponse;
import com.smartwasser.yunzhishui.net.HttpLoader;
import com.smartwasser.yunzhishui.rmonactivity.RmonStateActivity;
import com.smartwasser.yunzhishui.rmonactivity.SelectResultActivity;
import com.smartwasser.yunzhishui.utils.ConstantsYunZhiShui;
import com.smartwasser.yunzhishui.utils.DialogTimeUtils;
import com.smartwasser.yunzhishui.utils.PopListViewUtils;
import com.smartwasser.yunzhishui.utils.PopupWindowUtils;
import com.smartwasser.yunzhishui.utils.TimeUtils;

import org.seny.android.utils.MyToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by 15810 on 2019/3/1.
 */

public class FacilityStateActivity extends BaseActivity implements View.OnClickListener,HttpLoader.ResponseListener{
    private List<String> mlist;
    private ListView minitListView;
    private LinearLayout contentView;
    private Toolbar toolbar;
    private ImageButton button_menu;
    private TextView tv_toolbar;
    private TextView mRightTitle;
    private WebView mWdebView;

    private TimeUtils time=new TimeUtils();
    private BusinessUnitResponse mBusinessUnit;
    private MyBusinesAdapter myBusinesAdapter;
    private String code="";
    private BuildingResponse mBuilding;
    private  MyBuildingAdapter myBuildingAdapter;
    private String buildCode="";
    private QuotaResponse mQuota;
    private MyQuotaAdapter myQuotaAdapter;
    private String indeCode="";
    private ListView minitListView2,
            minitListView5,minitListView6,minitListView7;
    private DialogTimeUtils dialog=new DialogTimeUtils(this);
    private PopListViewUtils plu=new PopListViewUtils(this);
    private EventNormSelect event=new EventNormSelect();
    private EditText ed_facility_unit;
    private EditText ed_facility_goujian;
    private EditText ed_facility_yibiao;
    private EditText ed_facility_type;
    private EditText ed_facility_strattime;
    private EditText ed_facility_endtime;
    private Button ed_facility_btn;

    @Override
    protected int initContentView() {
        return R.layout.activity_facility_state;
    }

    @Override
    protected void initView() {
        contentView = findViewById(R.id.facility_contentView);
        button_menu= (ImageButton) findViewById(R.id.button_menu);
        mRightTitle = (TextView) findViewById(R.id.right_title);
        tv_toolbar= (TextView) findViewById(R.id.tv_toolbar);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        mWdebView= (WebView) findViewById(R.id.facility_wb);
        //进行webwiev的一堆设置
        //开启本地文件读取（默认为true，不设置也可以）
        mWdebView.getSettings().setAllowFileAccess(true);
        //开启脚本支持
        mWdebView.getSettings().setJavaScriptEnabled(true);
        mWdebView.loadUrl("file:///android_asset/myechart_type3.html");

        ed_facility_unit = findViewById(R.id.ed_facility_unit);
        ed_facility_goujian = findViewById(R.id.ed_facility_goujian);
        ed_facility_yibiao = findViewById(R.id.ed_facility_yibiao);
        ed_facility_type = findViewById(R.id.ed_facility_type);
        ed_facility_strattime = findViewById(R.id.ed_facility_strattime);
        ed_facility_endtime = findViewById(R.id.ed_facility_endtime);
        ed_facility_btn = findViewById(R.id.ed_facility_btn);

        ed_facility_unit.setOnClickListener(this);
        ed_facility_goujian.setOnClickListener(this);
        ed_facility_yibiao.setOnClickListener(this);
        ed_facility_type.setOnClickListener(this);
        ed_facility_strattime.setOnClickListener(this);
        ed_facility_endtime.setOnClickListener(this);
        ed_facility_btn.setOnClickListener(this);

    }

    @Override
    protected void initListener() {
        initDisplayOpinion();

        initAdapter();


        mRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btnText = mRightTitle.getText().toString();
                if ("曲线".equals(btnText)){
                    mWdebView.loadUrl("javascript:doCreatChart('line',[0,-1,0,0,-1,-2],['运行 1','停止 0','故障 -1'])");
                    contentView.setVisibility(View.GONE);
                    mWdebView.setVisibility(View.VISIBLE);
                    mRightTitle.setText("表格");
                }else {
                    contentView.setVisibility(View.VISIBLE);
                    mWdebView.setVisibility(View.GONE);
                    mRightTitle.setText("曲线");
                }

            }
        });
    }

    @Override
    protected void initData() {
        button_menu.setVisibility(View.VISIBLE);
        button_menu.setBackgroundResource(R.drawable.fanhu);
        toolbar.setTitle("");
        tv_toolbar.setText("设备状态查询");
        setSupportActionBar(toolbar);
        mRightTitle.setText("曲线");
    }


    private void initAdapter() {
        //构造假数据
        ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("行号");
        titleList.add("时间");
        titleList.add("数值");
        mTableDatas.add(titleList);




        ArrayList<CountBean> mRowDatas = new ArrayList<CountBean>();
        for (int i=0;i<20;i++){
            int num = (int) ((Math.random() * 9 + 1) * 100000);
            CountBean bean2 = new CountBean();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            String t=format.format(new Date());
            bean2.setT0(i+"");
            bean2.setT2(bean2+"m3/h");
            bean2.setT1(t);
            mRowDatas.add(bean2);
        }


        for (int i=0;i<mRowDatas.size();i++){
            ArrayList<String> fieldList = new ArrayList<>();
            fieldList.add(mRowDatas.get(i).getT0());
            fieldList.add(mRowDatas.get(i).getT1());
            fieldList.add(mRowDatas.get(i).getT2());
            mTableDatas.add(fieldList);
        }

        final LockTableView mLockTableView = new LockTableView(this, contentView, mTableDatas);
        Log.e("表格加载开始", "当前线程：" + Thread.currentThread());
        mLockTableView.setLockFristColumn(false) //是否锁定第一列
                .setLockFristRow(true) //是否锁定第一行
                .setMaxColumnWidth(200) //列最大宽度
                .setMinColumnWidth(60) //列最小宽度
//                .setColumnWidth(1,30) //设置指定列文本宽度
//                .setColumnWidth(0,20) //设置指定列文本宽度
//                .setColumnWidth(1,50)
//                .setColumnWidth(0,50)
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
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCBUSINESS
                && response instanceof BusinessUnitResponse) {
            mBusinessUnit= (BusinessUnitResponse) response;
            if ("00000".equals( mBusinessUnit.getErrorCode())) {
                myBusinesAdapter = new MyBusinesAdapter();
                minitListView5.setAdapter(myBusinesAdapter);
                minitListView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ed_facility_unit.setText(mBusinessUnit.getData().getComboboxList().get(position).getText());
                        code = mBusinessUnit.getData().getComboboxList().get(position).getId();
                        PopupWindowUtils.closePopupWindow();
                    }
                });
            }
        }
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCBUILDING
                && response instanceof BuildingResponse) {
            mBuilding = (BuildingResponse) response;
            if ("00000".equals(mBuilding.getErrorCode())) {
                myBuildingAdapter = new MyBuildingAdapter();
                minitListView6.setAdapter( myBuildingAdapter);
                minitListView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ed_facility_goujian.setText(mBuilding.getData().getComboboxList().get(position).getText());
                        buildCode = mBuilding.getData().getComboboxList().get(position).getId();
                        PopupWindowUtils.closePopupWindow();
                    }
                });

            }
        }
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCQUOTA
                && response instanceof QuotaResponse) {
            mQuota = (QuotaResponse) response;
            if ("00000".equals(mQuota.getErrorCode())) {
                myQuotaAdapter = new MyQuotaAdapter();
                minitListView7.setAdapter( myQuotaAdapter);
                minitListView7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ed_facility_yibiao.setText( mQuota.getData().getComboboxList().get(position).getText());
                        indeCode = mQuota.getData().getComboboxList().get(position).getId();
                        PopupWindowUtils.closePopupWindow();
                    }
                });

            }
        }
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCRMONSELECT
                && response instanceof RmonSelectResponse) {
            RmonSelectResponse  mQuota = (RmonSelectResponse) response;
            Log.i("00000",mQuota.getErrorCode());
            if("00014".equals(mQuota.getErrorCode())){
                Toast.makeText(FacilityStateActivity.this, "此时间段没有数据", Toast.LENGTH_SHORT).show();
            }
            if("00000".equals( mQuota.getErrorCode())){
                String sb=ed_facility_yibiao.getText().toString();
                event.setRmon(mQuota);
                event.setUserName(sb);
                EventBus.getDefault().postSticky(event);
                Intent intent=new Intent(FacilityStateActivity.this,SelectResultActivity.class);
                startActivity(intent);
            }
            dismissProgressDialog();
        }
    }
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        MyToast.show(this, "error: " + error.getMessage());
    }
    private ListView initListView7(){
        ListView mListViews = new ListView(this);
        mListViews.setDividerHeight(0);
        mListViews.setBackgroundResource(R.drawable.listview_background);
        // 去掉右侧垂直滑动条
        mListViews.setVerticalScrollBarEnabled(false);
        /**发起网络请求*/
        HashMap<String, Object> prams = new HashMap<>();
        prams.put("businessCode",code);
        prams.put("buildCode",buildCode);
        prams.put("dataType2","D1");
        HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCQUOTA, prams,
                QuotaResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCQUOTA, this, false).setTag(this);
        return mListViews;
    }
    private ListView initListView6(){
        ListView mListViews = new ListView(this);
        mListViews.setDividerHeight(0);
        mListViews.setBackgroundResource(R.drawable.listview_background);
        // 去掉右侧垂直滑动条
        mListViews.setVerticalScrollBarEnabled(false);
        /**发起网络请求*/
        HashMap<String, Object> prams = new HashMap<>();
        prams.put("businessCode", code);
        HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCBUILDING, prams,
                BuildingResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCBUILDING, this, false).setTag(this);
        return mListViews;
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
        return mListViews;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_fan:
                finish();
                break;
            case R.id.ed_facility_unit:
                /**单位*/
                minitListView5 = initListView5();
                PopupWindowUtils.showPopupWindow(minitListView5, ed_facility_unit);
                break;
            case R.id.ed_facility_goujian:
                /**构筑物*/
                String s = ed_facility_goujian.getText().toString();
                if ("".equals(s) || "null".equals(s) || s == null) {
                    Toast.makeText(FacilityStateActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                minitListView6 = initListView6();
                PopupWindowUtils.showPopupWindow(minitListView6, ed_facility_goujian);
                break;
            case R.id.ed_facility_yibiao:
                /**指标*/
                String s2 = ed_facility_yibiao.getText().toString();
                if ("".equals(s2) || "null".equals(s2) || s2 == null) {
                    Toast.makeText(FacilityStateActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                String s3 = ed_facility_yibiao.getText().toString();
                if ("".equals(s3) || "null".equals(s3) || s3 == null) {
                    Toast.makeText(FacilityStateActivity.this, "请选择构筑物", Toast.LENGTH_SHORT).show();
                    return;
                }
                minitListView7 = initListView7();
                PopupWindowUtils.showPopupWindow(minitListView7, ed_facility_yibiao);
                break;
            case R.id.ed_facility_strattime:
                /**开始时间*/
                dialog.show(ed_facility_strattime);
                dialog.showTime();
                break;
            case R.id.ed_facility_endtime:
                /**结束时间*/
                dialog.show(ed_facility_endtime);
                dialog.showTime();
                break;
//            case R.id.ed_kuaijie_muchtime:
//                /**快捷时间*/
//                // 初始化ListView控件和里边的数据
//                minitListView2 = plu.initListView();
//                PopupWindowUtils.showPopupWindow(minitListView2, ed_kuaijie_muchtime);
//                minitListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        List<String> list = time.showTimeShortcut();
//                        if (position >= 1) {
//                            ed_muchtrend_strattime.setText(list.get(position * 2 - 2));
//                            ed_muchtrend_endtime.setText(list.get(position * 2 - 1));
//                            ed_kuaijie_muchtime.setText(plu.mListView.get(position));
//                        }
//                        /**关闭popupwindow*/
//                        PopupWindowUtils.closePopupWindow();
//                    }
//                });
//                break;
            case R.id.ed_facility_btn:
                /**发起网络请求*/

                String dw=ed_facility_unit.getText().toString();
                if("".equals(dw)||dw==null||"null".equals(dw)){
                    Toast.makeText(FacilityStateActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                String gzw=ed_facility_goujian.getText().toString();
                if("".equals(gzw)||gzw==null||"null".equals(gzw)){
                    Toast.makeText(FacilityStateActivity.this, "请选择构筑物", Toast.LENGTH_SHORT).show();
                    return;
                }
                String sb=ed_facility_yibiao.getText().toString();
                if("".equals(sb)||sb==null||"null".equals(sb)){
                    Toast.makeText(FacilityStateActivity.this, "请选择设备", Toast.LENGTH_SHORT).show();
                    return;
                }
                String start=ed_facility_strattime.getText().toString();
                String end=ed_facility_endtime.getText().toString();
                if("".equals(start)||start==null||"null".equals(start)){
                    Toast.makeText(FacilityStateActivity.this, "请选择开始时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                if("".equals(end)||end==null||"null".equals(end)){
                    Toast.makeText(FacilityStateActivity.this, "请选择结束时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                showProgressDialog();
                HashMap<String, Object> prams = new HashMap<>();
                prams.put("businessCode",code);
                prams.put("buildCode",buildCode);
                prams.put("indeCode",indeCode);
                prams.put("dataTimeFrom",start);
                prams.put("dataTimeTo", end);
                HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCRMONSELECT, prams,
                        RmonSelectResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCRMONSELECT, this, false).setTag(this);
                showProgressDialog();
                break;
        }
    }

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
                convertView = View.inflate(FacilityStateActivity.this, R.layout.item_search, null);
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
    class  MyBuildingAdapter extends BaseAdapter{
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
                convertView = View.inflate(FacilityStateActivity.this, R.layout.item_search, null);
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
    class MyQuotaAdapter extends BaseAdapter{
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
                convertView = View.inflate(FacilityStateActivity.this, R.layout.item_search, null);
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
    class ViewHolder{
        TextView v_listview_item_number;
    }
}
