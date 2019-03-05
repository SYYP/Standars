package com.smartwasser.yunzhishui.pager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartwasser.yunzhishui.Activity.DataMainActivity;
import com.smartwasser.yunzhishui.alarm.AlarmActivity;
import com.smartwasser.yunzhishui.alarm.StatisticsActivity;
import com.smartwasser.yunzhishui.floodcontrolactivity.FloodcontrolActivity;
import com.smartwasser.yunzhishui.productionactivity.ProductionActivity;
import com.smartwasser.yunzhishui.purchaseactivity.PurchaseActivity;
import com.smartwasser.yunzhishui.record.HistoryRecordActivity;
import com.smartwasser.yunzhishui.rmonactivity.RmonActivity;
import com.smartwasser.yunzhishui.sludgeactivity.TrafficMainActivity;
import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.deviceactivity.DeviceActivity;

/**
 * Created by huangmengjie on 2016/home/28 0028.
 */
public class HomePager extends BasePager implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout downRefresh;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private String[] mText ={"远程监控","预报预警","汇总统计","泵站监测","历史查询","数据分析","远程控制"};
//    private String[] mText ={"生产数据","生产成本","远程监控","设备运行","污泥运输","生产指标","采购","防汛信息","工艺预测","远程调控"};
//    private int[] icon={R.drawable.home_11,R.drawable.home_22,R.drawable.home_33,R.drawable.home_44,R.drawable.home_55,R.drawable.home_66,R.drawable.home_77,R.drawable.home_99,R.drawable.home_88,R.drawable.home_10};
    private int[] icon={R.drawable.home_33,R.drawable.home_11,R.drawable.home_22,R.drawable.home_44,R.drawable.home_66,R.drawable.home_77,R.drawable.home_88};

    public HomePager(Context context) {
        super(context);
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            downRefresh.setRefreshing(false);
        }
    };
    @Override
    public View initViews(){
        view=View.inflate(context, R.layout.home_pager,null);
        downRefresh= (SwipeRefreshLayout) view.findViewById(R.id.srf);
        recyclerView= (RecyclerView)view.findViewById(R.id.recyclerview);
        return view;
    }
    @Override
    public void initDatas(){
        downRefresh.setOnRefreshListener(this);
        /**
         * 设置下拉刷新的颜色
         */
        downRefresh.setColorSchemeResources(android.R.color.holo_blue_bright
                , android.R.color.holo_orange_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_light
        );
        downRefresh.setBackgroundColor(Color.WHITE);
        LinearLayoutManager layout = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        adapter = new MyAdapter(context, true);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onRefresh() {
        Toast.makeText(context, "正在刷新,请稍等", Toast.LENGTH_SHORT).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.obtainMessage().sendToTarget();
            }
        },5000);
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        private Context context;
        private boolean flag;
        public MyAdapter(Context context, boolean flag) {
            this.context = context;
            this.flag = flag;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //
            View view= LayoutInflater.from(context).inflate(R.layout.grild_item,parent,false);
            return new MyViewHolder(view);
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.setData(position);
        }
        @Override
        public int getItemCount() {
            return mText.length;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv_home;
            ImageView iv_home;
            RelativeLayout itemView;
            public MyViewHolder(View itemView) {
                super(itemView);
                tv_home= (TextView)itemView.findViewById(R.id.tv_home);
                iv_home= (ImageView)itemView.findViewById(R.id.iv_home);
                this.itemView = (RelativeLayout) itemView;
                /**给recyclerView注册点击事件*/
                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        int position= (int)v.getTag(R.id.recyclerview);
                        switch (position){
                            case 0:
                                /**跳到远程监控*/
                                Intent intent5=new Intent(context,RmonActivity.class);
                                context.startActivity(intent5);
//                                /**跳转到生产数据的主页面*/
//                                Intent intent=new Intent(context, DataMainActivity.class);
//                                context.startActivity(intent);
                                break;
                            case 1:
                                /**跳到成本管理*/

//                                Intent intent2=new Intent(context, ProductionActivity.class);
                                Intent intent2=new Intent(context, AlarmActivity.class);
                                context.startActivity(intent2);
                                break;
                            case 2:
//                                /**跳到远程监控*/
//                                Intent intent5=new Intent(context,RmonActivity.class);
                                Intent intent=new Intent(context,StatisticsActivity.class);
                                context.startActivity(intent);
                                break;
                            case 3:
                                /**设备运行*/
                                Intent intent3=new Intent(context,DeviceActivity.class);
                                context.startActivity(intent3);
                                break;
                            case 4:
                                /**跳转到污泥运输*/
                                Intent intent4=new Intent(context,HistoryRecordActivity.class);
                                context.startActivity(intent4);
                                break;
                            case 7:
                                /**防汛系统*/
                                Intent intent8=new Intent(context, FloodcontrolActivity.class);
                                context.startActivity(intent8);
                                break;
                            case 6:
                                /**采购*/
//                                Intent intent6=new Intent(context,PurchaseActivity.class);
//                                context.startActivity(intent6);
                                break;
                        }
                    }
                });
            }
            /**
             * 设置数据的方法
             * @param position
             */
            private void setData(int position){
                iv_home.setImageResource(icon[position]);
                tv_home.setText(mText[position]);
                itemView.setTag(R.id.itemView, position);
                itemView.setTag(R.id.recyclerview, position);
            }

        }
    }
}
