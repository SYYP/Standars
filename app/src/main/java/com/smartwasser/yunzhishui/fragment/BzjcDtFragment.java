package com.smartwasser.yunzhishui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.smartwasser.yunzhishui.Activity.BzssjcActivity;
import com.smartwasser.yunzhishui.R;

/**
 * Name: BzjcDtFragment
 * Author: Administrator
 * Comment: //TODO
 * Date: 2019-02-28 10:30
 */
public class BzjcDtFragment extends Fragment {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    // 定位相关
    LocationClient mLocClient;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    public MyLocationListenner myListener = new MyLocationListenner();
    OverlayOptions option = null;
    OverlayOptions option1 = null;
    OverlayOptions option2 = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bzssjc, null);
        //初始化地图
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        doLocation();
        addMark();
    }

    /**
     * 进行定位
     */
    private void doLocation() {
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(16));
        mLocClient = new LocationClient(getActivity());
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

    /**
     * 添加mark
     */
    private void addMark() {
        // 构建Marker图标
        BitmapDescriptor bitmap = null;
        BitmapDescriptor bitmap2 = null;
        BitmapDescriptor bitmap3 = null;
        BitmapDescriptor bitmap4 = null;
        bitmap2 = BitmapDescriptorFactory.fromResource(R.drawable.icon_20);
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_21);
        bitmap3 = BitmapDescriptorFactory.fromResource(R.drawable.icon_22);
        bitmap4 = BitmapDescriptorFactory.fromResource(R.drawable.icon_23);
        LatLng llA;
        LatLng llB;
        LatLng llC;
        Marker marker = null;
        Marker marker1 = null;
        Marker marker2 = null;
        //设置经纬度
        for (int i = 0; i < 10; i++) {
            llA = new LatLng(40.086318789 + i, 116.320436458 + i);
            llB = new LatLng(40.086318789 + i + 1, 116.320436458 + i - 1);
            llC = new LatLng(40.086318789 + i - 1, 116.320436458 + i + 1);
            option = new MarkerOptions().position(llA).icon(bitmap3);
            option1 = new MarkerOptions().position(llB).icon(bitmap4);
            option2 = new MarkerOptions().position(llB).icon(bitmap2);
            marker1 = (Marker) mBaiduMap.addOverlay(option);
            marker = (Marker) mBaiduMap.addOverlay(option1);
            marker2 = (Marker) mBaiduMap.addOverlay(option2);
            //为marker添加识别标记信息
            Bundle bundle = new Bundle();
            bundle.putSerializable("info", "你好吗");
            bundle.putSerializable("info2", "这是一个测试" + i);
            bundle.putSerializable("info3", "好的没有问题");
            marker.setExtraInfo(bundle);
            marker1.setExtraInfo(bundle);
            marker2.setDraggable(true);
            marker.setDraggable(true);
            marker1.setDraggable(true);

        }

    }
}
