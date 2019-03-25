package com.smartwasser.yunzhishui.rmonactivity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.bean.EventSearchResponse;
import com.smartwasser.yunzhishui.net.HttpLoader;
import com.smartwasser.yunzhishui.utils.ConstantsYunZhiShui;
import com.smartwasser.yunzhishui.utils.GetScreenParam;

import java.util.Map;

import de.greenrobot.event.EventBus;
/**
 * Created by huangmengjie on 2016/7/29 0029.
 */
public class RmonRealTimeActivity extends AppCompatActivity {
    private WebView webview;
    private ImageButton menu;
    private TextView tv_toolbar;
    private EventSearchResponse ese;
    private  Map<String,String> map;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        /**注册一个EventBus*/
        EventBus.getDefault().registerSticky(this);
        webview= (WebView)findViewById(R.id.webview);
        menu= (ImageButton) findViewById(R.id.button_menu);
        tv_toolbar= (TextView) findViewById(R.id.tv_toolbar);
        menu.setBackgroundResource(R.drawable.fanhu);
        tv_toolbar.setText(ese.getContractunitName());

        initData();
    }
    private void initData() {
        int width=GetScreenParam.getScreenWidth(this);
        //-(int)(GetScreenParam.getScreenHeight(this)*0.1260)
        int height=GetScreenParam.getScreenHeight(this);
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams)webview.getLayoutParams();
        linearParams.height = height;
        linearParams.width = width;
        /**将屏幕宽高设置给webView*/
        map = HttpLoader.generateHeaders();


        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setLayoutParams(linearParams);
        removeCookie();
        syncCookie(ConstantsYunZhiShui.URL_ZXJCMONITOR + "/" + ese.getAddId(), map.get("Cookie"));
        webview.loadUrl(ConstantsYunZhiShui.URL_ZXJCMONITOR + "/" +ese.getAddId(), map);

        Log.d("Uri", webview.getUrl());
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url, map);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void onEvent(EventSearchResponse ese){
        this.ese=ese;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    /**
     * 将cookie同步到WebView
     * @param url WebView要加载的url
     * @param cookie 要同步的cookie
     * @return true 同步cookie成功，false同步cookie失败
     * @Author JPH
     */
    public static boolean syncCookie(String url,String cookie) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(url,cookie);//如果没有特殊需求，这里只需要将session id以"key=value"形式作为cookie即可
        String newCookie = cookieManager.getCookie(url);
        return TextUtils.isEmpty(newCookie)?false:true;
    }
    private void removeCookie() {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }
}
