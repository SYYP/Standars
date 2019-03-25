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
import com.smartwasser.yunzhishui.bean.QuotaResponse;
import com.smartwasser.yunzhishui.bean.RBResponse;
import com.smartwasser.yunzhishui.bean.RmonMenuResponse;
import com.smartwasser.yunzhishui.bean.RundataResponse;
import com.smartwasser.yunzhishui.net.HttpLoader;
import com.smartwasser.yunzhishui.rmonactivity.RunDataReslutActivity;
import com.smartwasser.yunzhishui.utils.ConstantsYunZhiShui;
import com.smartwasser.yunzhishui.utils.DialogTimeUtils;
import com.smartwasser.yunzhishui.utils.PopListViewUtils;
import com.smartwasser.yunzhishui.utils.PopupWindowUtils;
import com.smartwasser.yunzhishui.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by 15810 on 2019/3/1.
 */

public class WaterCountActivity extends BaseActivity implements View.OnClickListener,HttpLoader.ResponseListener{
    private List<String> mlist;
    private ListView minitListView;
    private LinearLayout contentView;
    private Toolbar toolbar;
    private ImageButton button_menu;
    private TextView tv_toolbar;
    private TextView mRightTitle;
    private WebView mWdebView;
    private EditText ed_water_unit;
    private EditText ed_water_goujian;
    private EditText ed_water_yibiao;
    private EditText ed_water_name;
    private EditText ed_water_type;
    private EditText ed_water_strattime;
    private EditText ed_water_endtime;
    private ListView minitListView2,minitListView3,minitListView4,
            minitListView5,minitListView6,minitListView7;
    private DialogTimeUtils dialog=new DialogTimeUtils(this);
    private PopListViewUtils plu=new PopListViewUtils(this);
    private TimeUtils time=new TimeUtils();
    private BusinessUnitResponse mBusinessUnit;
    private MyBusinesAdapter myBusinesAdapter;
    private String code="";
    private BuildingResponse mBuilding;
    private MyBuildingAdapter myBuildingAdapter;
    private String buildCode="";
    private QuotaResponse mQuota;
    private MyQuotaAdapter myQuotaAdapter;
    private String indeCode="";
    private Button run_data_btn;
    private TextView tv_yibiao;
    private Button water_data_btn;
    @Override
    protected int initContentView() {
        return R.layout.activity_water_count;
    }

    @Override
    protected void initView() {
        contentView = findViewById(R.id.water_contentView);
        button_menu= (ImageButton) findViewById(R.id.button_menu);
        mRightTitle = (TextView) findViewById(R.id.right_title);
        tv_toolbar= (TextView) findViewById(R.id.tv_toolbar);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        mWdebView= (WebView) findViewById(R.id.water_wb);


        ed_water_unit= (EditText) findViewById(R.id.ed_water_unit);
        ed_water_goujian= (EditText) findViewById(R.id.ed_water_goujian);
        ed_water_yibiao= (EditText) findViewById(R.id.ed_water_yibiao);
        ed_water_name= (EditText) findViewById(R.id.ed_water_name);
        ed_water_type= (EditText) findViewById(R.id.ed_water_type);
        ed_water_strattime= (EditText) findViewById(R.id.ed_water_strattime);
        ed_water_endtime= (EditText) findViewById(R.id.ed_water_endtime);
        water_data_btn= (Button) findViewById(R.id.water_data_btn);

        ed_water_unit.setOnClickListener(this);
        ed_water_goujian.setOnClickListener(this);
        ed_water_yibiao.setOnClickListener(this);
        ed_water_name.setOnClickListener(this);
        ed_water_type.setOnClickListener(this);
        ed_water_strattime.setOnClickListener(this);
        ed_water_endtime.setOnClickListener(this);
        water_data_btn.setOnClickListener(this);
        //进行webwiev的一堆设置
        //开启本地文件读取（默认为true，不设置也可以）
        mWdebView.getSettings().setAllowFileAccess(true);
        //开启脚本支持
        mWdebView.getSettings().setJavaScriptEnabled(true);
        mWdebView.loadUrl("file:///android_asset/myechart.html");
    }

    @Override
    protected void initListener() {
        initDisplayOpinion();

        initAdapter();


        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        button_menu.setVisibility(View.VISIBLE);
        button_menu.setBackgroundResource(R.drawable.fanhu);
        toolbar.setTitle("");
        RmonMenuResponse.DataBean dataBean = (RmonMenuResponse.DataBean) getIntent().getSerializableExtra("title");
        tv_toolbar.setText(dataBean.getFuncnamech());
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_fan:
                finish();
                break;
            case R.id.ed_water_unit:
                /**单位*/
                minitListView5=initListView5();
                PopupWindowUtils.showPopupWindow( minitListView5,ed_water_unit);
                break;
            case R.id.ed_water_goujian:
                /**构筑物*/
                String s=ed_water_goujian.getText().toString();
                if("".equals(s)||"null".equals(s)||s==null){
                    Toast.makeText(WaterCountActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                minitListView6=initListView6();
                PopupWindowUtils.showPopupWindow( minitListView6,ed_water_goujian);
                break;
            case R.id.ed_water_yibiao:
                /**指标*/
                String s2=ed_water_yibiao.getText().toString();
                if("".equals(s2)||"null".equals(s2)||s2==null){
                    Toast.makeText(WaterCountActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                String  s3=ed_water_yibiao.getText().toString();
                if("".equals(s3)||"null".equals(s3)||s3==null){
                    Toast.makeText(WaterCountActivity.this, "请选择构筑物", Toast.LENGTH_SHORT).show();
                    return;
                }
                minitListView7=initListView7();
                PopupWindowUtils.showPopupWindow( minitListView7,ed_water_yibiao);
                break;
            case R.id.ed_water_strattime:
                /**开始时间*/
                dialog.show(ed_water_strattime);
                dialog.showTime();
                break;
            case R.id.ed_water_endtime:
                /**结束时间*/
                dialog.show(ed_water_endtime);
                dialog.showTime();
                break;
            case R.id.ed_water_type:
                minitListView3= plu.initListView9();
                PopupWindowUtils.showPopupWindow(minitListView3, ed_water_type);
                minitListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ed_water_type.setText(plu.mListView9.get(position));
                        if("精细查询".equals( ed_water_type.getText().toString())){
//                            image_shunshi.setVisibility(View.GONE);
//                            ed_shunshi.setVisibility(View.GONE);
                        }else{
//                            image_shunshi.setVisibility(View.VISIBLE);
//                            ed_shunshi.setVisibility(View.VISIBLE);
                        }
                        PopupWindowUtils.closePopupWindow();
                    }
                });
                break;
            case R.id.ed_shunshi:
//                minitListView4= plu.initListView9();
//                PopupWindowUtils.showPopupWindow(minitListView4, ed_shunshi);
//                minitListView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        ed_shunshi.setText(plu.mListView9.get(position));
//                        PopupWindowUtils.closePopupWindow();
//                    }
//                });
                break;
            case R.id.ed_water_name:
                minitListView2= plu.initListView8();
                PopupWindowUtils.showPopupWindow(minitListView2, ed_water_name);
                minitListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ed_water_name.setText(plu.mListView8.get(position));
                        if("精细查询".equals( ed_water_name.getText().toString())){
//                            image_shunshi.setVisibility(View.GONE);
//                            ed_shunshi.setVisibility(View.GONE);
                        }else{
//                            image_shunshi.setVisibility(View.VISIBLE);
//                            ed_shunshi.setVisibility(View.VISIBLE);
                        }
                        PopupWindowUtils.closePopupWindow();
                    }
                });
                break;
            case R.id.run_data_btn:
                /**查询*/

                String dw=ed_water_unit.getText().toString();
                if("".equals(dw)||dw==null||"null".equals(dw)){
                    Toast.makeText(WaterCountActivity.this, "请选择单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                String gzw=ed_water_goujian.getText().toString();
                if("".equals(gzw)||gzw==null||"null".equals(gzw)){
                    Toast.makeText(WaterCountActivity.this, "请选择构筑物", Toast.LENGTH_SHORT).show();
                    return;
                }
                String sb=ed_water_yibiao.getText().toString();
                if("".equals(sb)||sb==null||"null".equals(sb)){
                    Toast.makeText(WaterCountActivity.this, "请选择设备", Toast.LENGTH_SHORT).show();
                    return;
                }
                String start=ed_water_strattime.getText().toString();
                String end=ed_water_endtime.getText().toString();
                if("".equals(start)||start==null||"null".equals(start)){
                    Toast.makeText(WaterCountActivity.this, "请选择开始时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                String xiao=ed_water_type.getText().toString();
                if("".equals(xiao)||xiao==null||"null".equals(xiao)){
                    Toast.makeText(WaterCountActivity.this, "请选择查询模式", Toast.LENGTH_SHORT).show();
                    return;
                }
                String shun=ed_water_type.getText().toString();
                /**发起网络请求*/
                showProgressDialog();
                HashMap<String, Object> prams = new HashMap<>();
                if(!xiao.equals("精细查询")){
                    if(shun.equals("瞬时值")){
                        prams.put("indeName","INS");
                    }else if(shun.equals("峰值")){
                        prams.put("indeName","MAX");
                    }else if(shun.equals("谷值")){
                        prams.put("indeName","MIN");
                    }else if(shun.equals("平均值")){
                        prams.put("indeName","AVG");
                    }
                }
                if(xiao.equals("小时查询")){
                    prams.put("pattern","findbyhour");
                }else if(xiao.equals("精细查询")){
                    prams.put("pattern","findbyhis");
                }
                prams.put("businessCode",code);
                prams.put("buildCode",buildCode);
                prams.put("indeCode",indeCode);
                prams.put("dataTimeFrom",start);
                prams.put("dataTimeTo",end);
                HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCRUNDATA, prams,
                        RundataResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCRUNDATA, this, false).setTag(this);
                showProgressDialog();
                break;

        }
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
                        ed_water_unit.setText(mBusinessUnit.getData().getComboboxList().get(position).getText());
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
                        ed_water_goujian.setText(mBuilding.getData().getComboboxList().get(position).getText());
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
                        ed_water_yibiao.setText( mQuota.getData().getComboboxList().get(position).getText());
                        indeCode = mQuota.getData().getComboboxList().get(position).getId();
                        PopupWindowUtils.closePopupWindow();
                    }
                });

            }
        }
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCRUNDATA
                && response instanceof RundataResponse) {
            RundataResponse mRunData=(RundataResponse)response;
            if("00000".equals(mRunData.getErrorCode())){
                /**跳转到指定页面,并传值*/
                EventBus.getDefault().postSticky(mRunData);
                Intent intent=new Intent(WaterCountActivity.this,RunDataReslutActivity.class);
                startActivity(intent);
            }
            dismissProgressDialog();
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

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
                convertView = View.inflate(WaterCountActivity.this, R.layout.item_search, null);
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
        prams.put("dataType2","A1");
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

    /*适配器*/
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
                convertView = View.inflate(WaterCountActivity.this, R.layout.item_search, null);
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
                convertView = View.inflate(WaterCountActivity.this, R.layout.item_search, null);
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
