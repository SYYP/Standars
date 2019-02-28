package com.smartwasser.yunzhishui.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.utils.PopListViewUtils;

/**
 * Name: BzssjcActivity
 * Author: Administrator
 * Comment: //TODO
 * Date: 2019-02-23 12:26
 * 泵站实时监测
 */
public class BzssjcActivity extends Activity {

    private ImageButton mButtonMenu;
    /**
     * 你好啊
     */
    private TextView mTvToolbar;
    private Toolbar mToolbar;
    private ImageButton mNormSer;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    public MyLocationListenner myListener = new MyLocationListenner();
    // 定位相关
    LocationClient mLocClient;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private String info,info2,info3;
    private PopListViewUtils plu=new PopListViewUtils(this);
    OverlayOptions option=null;
    OverlayOptions option1=null;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bzssjc);
        mButtonMenu = (ImageButton) findViewById(R.id.button_menu);
        mTvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNormSer = (ImageButton) findViewById(R.id.norm_ser);
        mTvToolbar.setText("泵站实时监测");
         //初始化地图
        mMapView = (MapView) findViewById(R.id.mapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        doLocation();
         addMark();
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                info = (String) marker.getExtraInfo().get("info");
                info2 = (String) marker.getExtraInfo().get("info2");
                info3 = (String) marker.getExtraInfo().get("info3");
                /**弹出popupwind*/
                View view = View.inflate(BzssjcActivity.this, R.layout.activity_markdt, null);
                final InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(view), marker.getPosition(), -80,
                new InfoWindow.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick() {
                       mBaiduMap.hideInfoWindow();
                    }
                });
                mBaiduMap.showInfoWindow(mInfoWindow);

                //获取到mark的信息后，创建dialog
                Toast.makeText(BzssjcActivity.this,info+info2+info3,Toast.LENGTH_SHORT).show();
                  addDialog();
                return false;
            }
        });
    }
    //创建dialog
    private void addDialog() {
//        BaseDialog.Builder builder = new BaseDialog.Builder(this);
//        final BaseDialog dialog = builder.setViewId(R.layout.dialog_yuyin)
//                //设置dialogpadding
//                .setPaddingdp(0, 10, 0, 10)
//                //设置显示位置
//                .setGravity(grary)
//                //设置动画
//                .setAnimation(animationStyle)
//                //设置dialog的宽高
//                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//                //设置触摸dialog外围是否关闭
//                .isOnTouchCanceled(true)
//                //设置监听事件
//                .builder();
//        dialog.show();

    }
    private void getZaiw(LatLng point, String s){
        //构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption = new TextOptions()
                .bgColor(0xAA000000)
                .fontSize(30)
                .fontColor(0xFFFFFFFF)
                .text(s)
                .rotate(0)
                .position(point);
        //在地图上添加该文字对象并显示
        mBaiduMap.addOverlay(textOption);
    }
    /**
     *   添加mark
     */
    private void addMark() {
        // 构建Marker图标
        BitmapDescriptor bitmap = null;
        BitmapDescriptor bitmap2 = null;
        BitmapDescriptor bitmap3= null;
        BitmapDescriptor bitmap4= null;
        bitmap2 = BitmapDescriptorFactory.fromResource(R.drawable.icon_20);
        bitmap= BitmapDescriptorFactory.fromResource(R.drawable.icon_21);
        bitmap3= BitmapDescriptorFactory.fromResource(R.drawable.icon_22);
        bitmap4= BitmapDescriptorFactory.fromResource(R.drawable.icon_23);
        LatLng llA;
        LatLng llB;

        Marker marker = null;
        Marker marker1 = null;
           //设置经纬度
        for (int i = 0; i < 10; i++) {
            llA = new LatLng(40.086318789+i,116.320436458+i);
            llB = new LatLng(40.086318789+i+1,116.320436458+i-1);
            option = new MarkerOptions().position(llA).icon(bitmap3);
            option1 = new MarkerOptions().position(llB).icon(bitmap4);
            marker1 = (Marker) mBaiduMap.addOverlay(option);
            marker = (Marker) mBaiduMap.addOverlay(option1);
            //为marker添加识别标记信息
            Bundle bundle = new Bundle();
            bundle.putSerializable("info","你好吗");
            bundle.putSerializable("info2","这是一个测试"+i);
            bundle.putSerializable("info3","好的没有问题");
            marker.setExtraInfo(bundle);
            marker1.setExtraInfo(bundle);
            marker.setDraggable(true);
            marker1.setDraggable(true );
            getZaiw(llB,"这是一个地点");
        }

    }
    /**
     * 进行定位
     */
    private void doLocation() {
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(16));
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }
    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

    }
}
