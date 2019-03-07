package com.smartwasser.yunzhishui.pager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.adapter.SimpleTreeListViewAdapter4;
import com.smartwasser.yunzhishui.adapter.SimpleTreeListViewAdapter5;
import com.smartwasser.yunzhishui.adapter.TreeListViewAdapter;
import com.smartwasser.yunzhishui.bean.EventSearchResponse;
import com.smartwasser.yunzhishui.bean.ProductResponse;
import com.smartwasser.yunzhishui.bean.RBResponse;
import com.smartwasser.yunzhishui.bean.RmonMenuResponse;
import com.smartwasser.yunzhishui.net.HttpLoader;
import com.smartwasser.yunzhishui.productionactivity.ContractQueActivity;
import com.smartwasser.yunzhishui.productionactivity.PaymentActivity;
import com.smartwasser.yunzhishui.rmonactivity.RmonRealTimeActivity;
import com.smartwasser.yunzhishui.rmonactivity.RmonStateActivity;
import com.smartwasser.yunzhishui.rmonactivity.RunDataActivity;
import com.smartwasser.yunzhishui.utils.ConstantsYunZhiShui;
import com.smartwasser.yunzhishui.utils.Node;
import com.smartwasser.yunzhishui.utils.TreeListUtils;

import org.seny.android.utils.MyToast;

import java.io.IOException;
import java.util.HashMap;

import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by huangmengjie on 2016/7/29 0029.
 */
public class RmonPager  extends BasePager implements HttpLoader.ResponseListener{
    private Context mContext;
    private ListView list_production;
    private RmonMenuResponse mProduct;
    private EventSearchResponse sear=new EventSearchResponse();
    private TreeListUtils tlu=new TreeListUtils();
    private SimpleTreeListViewAdapter5 SimleTreeadapter4;
    public RmonPager(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    public View initViews() {
        view=View.inflate(context, R.layout.pager_device,null);
        list_production=(ListView)view.findViewById(R.id.list_device);
        return view;
    }
    @Override
    public void initDatas() throws IllegalAccessException {
        HashMap<String, Object> prams = new HashMap<>();
        HttpLoader.get(ConstantsYunZhiShui.URL_ZXJCMENULIST, prams,
                RmonMenuResponse.class, ConstantsYunZhiShui.REQUEST_CODE_ZXJCMENULIST, this).setTag(this);
//        getRmonPageData();
    }
    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        if (requestCode == ConstantsYunZhiShui.REQUEST_CODE_ZXJCMENULIST
                && response instanceof RmonMenuResponse) {
            mProduct = (RmonMenuResponse) response;
            if ("00000".equals(mProduct.getErrorCode())) {
                tlu.initDataText7(mProduct);
                try {
                    SimleTreeadapter4 = new SimpleTreeListViewAdapter5(list_production, mContext, tlu.mDatas2, 0);
                    list_production.setAdapter(SimleTreeadapter4);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                SimleTreeadapter4.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                    @Override
                    public void onClick(Node node, int position) {
                        if (node.isleaf()) {
                            String s= mProduct.getData().get(0).getFuncurl();
                            String arr[]=s.split("\\?");
                            if(arr.length>=2){
                            String m[]=arr[1].split("=");
                            sear.setNodeId(m[1]);
                            }
                            switch (node.getId()) {
                                case "138372609457828586382":
                                    /**跳到清河*/
                                    EventBus.getDefault().postSticky(sear);
                                    Intent intent12 = new Intent(mContext,RmonRealTimeActivity.class);
                                    intent12.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    mContext.startActivity(intent12);
                                    break;
                                case "139962732492125113615":
                                    /**运行数据查询*/
                                    Intent intent13 = new Intent(mContext,RunDataActivity.class);
                                    intent13.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    mContext.startActivity(intent13);
                                    break;
                                case "139962732492125113616":
                                      /**设备运行状态*/
                                    Intent intent3= new Intent(mContext,RmonStateActivity.class);
                                    intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    mContext.startActivity(intent3);
                            }
                        }
                    }
                });
            }
        }
    }
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        MyToast.show(mContext, "error: " + error.getMessage());
    }




    public void getRmonPageData(){
        OkGo.get(ConstantsYunZhiShui.URL_ZXJCMENULIST)                            // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            String resultJson = response.body().string();
                            mProduct = new Gson().fromJson(resultJson, RmonMenuResponse.class);
                            if ("00000".equals(mProduct.getErrorCode())) {
                                tlu.initDataText7(mProduct);
                                try {
                                    SimleTreeadapter4 = new SimpleTreeListViewAdapter5(list_production, mContext, tlu.mDatas2, 0);
                                    list_production.setAdapter(SimleTreeadapter4);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                SimleTreeadapter4.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                                    @Override
                                    public void onClick(Node node, int position) {
                                        if (node.isleaf()) {
                                            String s= mProduct.getData().get(0).getFuncurl();
                                            String arr[]=s.split("\\?");
                                            if(arr.length>=2){
                                                String m[]=arr[1].split("=");
                                                sear.setNodeId(m[1]);
                                            }
                                            switch (node.getId()) {
                                                case "138372609457828586382":
                                                    /**跳到清河*/
                                                    EventBus.getDefault().postSticky(sear);
                                                    Intent intent12 = new Intent(mContext,RmonRealTimeActivity.class);
                                                    intent12.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    mContext.startActivity(intent12);
                                                    break;
                                                case "139962732492125113615":
                                                    /**运行数据查询*/
                                                    Intent intent13 = new Intent(mContext,RunDataActivity.class);
                                                    intent13.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    mContext.startActivity(intent13);
                                                    break;
                                                case "139962732492125113616":
                                                    /**设备运行状态*/
                                                    Intent intent3= new Intent(mContext,RmonStateActivity.class);
                                                    intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    mContext.startActivity(intent3);
                                            }
                                        }
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }


}
