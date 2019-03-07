package com.smartwasser.yunzhishui.net;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.smartwasser.yunzhishui.Activity.LoginActivity;
import com.smartwasser.yunzhishui.Activity.MainActivity;
import com.smartwasser.yunzhishui.App;
import com.smartwasser.yunzhishui.bean.ErrorResponse;
import com.smartwasser.yunzhishui.bean.RBResponse;
import com.smartwasser.yunzhishui.utils.SharedUtils;

import org.seny.android.utils.ALog;
import org.seny.android.utils.MD5Utils;
import org.seny.android.utils.MyToast;
import org.springframework.util.FileCopyUtils;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * 网络请求核心类，负责get,post请求，初始化RequestQueue和ImageLoader
 *
 * Created by xiongmc on 2015/12/23.
 */
public class HttpLoader {
    private static List<String> mHeaderList=new ArrayList<>();
    /**
     * 过滤重复请求(正在飞的请求集合)，保存当前正在消息队列中执行的request，其中key为requestCode.
     */
    private static final HashMap<Integer, Request> mInFlightRequests = new HashMap<>();
    /**
     * 消息队列，全局使用一个
     */
    private static RequestQueue mRequestQueue = Volley.newRequestQueue(App.application);
    /**
     * 图片加载工具，使用自定义的二级缓存机制
     */
    private static ImageLoader mImageLoader = new ImageLoader(mRequestQueue, new LevelTwoLruBitmapCache(App.application));


    /**
     * 以get方式发送gsonRequest请求，默认缓存请求结果
     * @param url       请求地址
     * @param params    GET请求参数，拼接在url后面，可以为null
     * @param clazz     字节码类型，GSON解析json字符串封装的数据类型(解析成哪种bean对象)
     * @param requestCode   请求码，每次请求对于一个requestCode，作为该请求的唯一标识
     * @param listener  处理响应的监听器
     * @return  Request对象，方便链式编程，比如说接着设置Tag
     */
    public static Request get(String url, Map<String, Object> params, Class<? extends RBResponse> clazz, final int requestCode, final ResponseListener listener) {
        return request(Request.Method.GET, url, params, clazz, requestCode, listener, true);
    }

    /**
     * 以get方式发送gsonRequest请求
     *
     * @param url       请求地址
     * @param params    GET请求参数，拼接在url后面，可以为null
     * @param clazz     字节码类型，GSON解析json字符串封装的数据类型(解析成哪种bean对象)
     * @param requestCode   请求码，每次请求对于一个requestCode，作为该请求的唯一标识
     * @param listener  处理响应的监听器
     * @param isCache     是否需要缓存本次响应的结果,没有网络时会使用本地缓存
     */
    public static Request get(String url, Map<String,Object> params, Class<? extends RBResponse> clazz, final int requestCode, final ResponseListener listener, boolean isCache) {
        return request(Request.Method.GET, url, params, clazz, requestCode, listener, isCache);
    }
    /**
     * 发送post方式的GsonRequest请求，默认缓存请求结果
     * @param url         请求地址
     * @param params      请求参数，可以为null
     * @param clazz       Clazz类型，用于GSON解析json字符串封装数据
     * @param requestCode 请求码 每次请求对应一个code作为改Request的唯一标识
     * @param listener    处理响应的监听器
     */
    public static Request post(String url, Map<String,Object> params, Class<? extends RBResponse> clazz, final int requestCode, final ResponseListener listener) {
        return request(Request.Method.POST, url, params, clazz, requestCode, listener, true);
    }

    /**
     * 发送post方式的GsonRequest请求
     *
     * @param url         请求地址
     * @param params      请求参数，可以为null
     * @param clazz       Clazz类型，用于GSON解析json字符串封装数据
     * @param requestCode 请求码 每次请求对应一个code作为改Request的唯一标识
     * @param listener    处理响应的监听器
     * @param isCache     是否需要缓存本次响应的结果,没有网络时会使用本地缓存
     */
    public static Request post(String url, Map<String,Object> params, Class<? extends RBResponse> clazz, final int requestCode, final ResponseListener listener, boolean isCache) {
        return request(Request.Method.POST, url, params, clazz, requestCode, listener, isCache);
    }


    /**
     * 发送gsonRequest请求
     * @param method        请求方式
     * @param url           请求地址
     * @param params        请求参数，可以为null
     * @param clazz         Clazz类型
     * @param requestCode   请求码
     * @param listener      处理响应的监听器
     * @param isCache       是否需要缓冲本次响应结果
     * @return
     */
    private static Request request(int method, String url, Map<String, Object> params, Class<? extends RBResponse> clazz, int requestCode, ResponseListener listener, boolean isCache) {
        Request request = mInFlightRequests.get(requestCode);
        if (request == null) {
            request = makeGsonRequest(method, url + buildGetParam(params), null, clazz, requestCode, listener, isCache);
            //首先尝试解析本地缓存供界面显示，然后再发起网络请求
            tryLoadCacheResponse(request, requestCode, listener);
            ALog.d("Handle request by network!");
            return addRequest(request, requestCode);
        } else {
            ALog.i("Hi guy,the request (RequestCode is " + requestCode + ")  is already in-flight , So Ignore!");
            return request;
        }
    }

    /**
     * 把请求添加到请求队列（相当于发起了网络请求）
     * @param requestCode   请求码，请求的唯一标识
     * @return  request，方便链式编程
     */
    private static Request addRequest(Request<?> request, int requestCode) {
        if (mRequestQueue != null && request != null) {
            mRequestQueue.add(request);
        }
        mInFlightRequests.put(requestCode, request);
        return request;
    }

    /**
     * 取消请求
     *
     * @param tag 请求TAG
     */
    public static void cancelRequest(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);//从请求队列中取消对应的任务
        }
        //同时在mInFlightRequests删除保存所有TAG匹配的Request
        Iterator<Map.Entry<Integer, Request>> it = mInFlightRequests.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Request> entry = it.next();
            Object rTag = entry.getValue().getTag();
            if (rTag != null && rTag.equals(tag)) {
                it.remove();
            }
        }
    }

    public static ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * 尝试从缓存中读取json数据
     * @param request       请求
     * @param requestCode   请求码
     * @param listener      结果响应监听
     */
    private static void tryLoadCacheResponse(Request request, int requestCode, ResponseListener listener) {
        ALog.d("Try to  load cache response first !");
        if (listener != null && request != null) {
            try {
                //获取缓存文件
                File cacheFile = new File(App.application.getCacheDir(), "" + MD5Utils.encode(request.getUrl()));
                StringWriter sw = new StringWriter();
                //读取缓存文件
                FileCopyUtils.copy(new FileReader(cacheFile), sw);
                if (request instanceof GsonRequest) {
                    //如果是GsonRequest，那么解析出本地缓存的json数据为GsonRequest
                    GsonRequest gr = (GsonRequest) request;
                    RBResponse response = (RBResponse) gr.getGson().fromJson(sw.toString(), gr.getClazz());
                    //传给onResponse，让前面的人用缓存数据
                    listener.onGetResponseSuccess(requestCode, response);
                    ALog.d("Load cache response success !");
                }
            } catch (Exception e) {
                ALog.w("No cache response ! " + e.getMessage());
            }
        }
    }

    /**
     * 遍历Map集合元素，构建一个get请求参数字符串
     *
     * @param params    get请求map集合
     * @return          get请求的字符串结构
     */
    private static String buildGetParam(Map<String, Object> params) {
        StringBuilder buffer = new StringBuilder();
        if (params != null) {
            buffer.append("?");
            for (Map.Entry<String,Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value =entry.getValue();
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value+"")) {
                    continue;
                }
                try {
                    buffer.append(URLEncoder.encode(key, "UTF-8"));
                    buffer.append("=");
                    buffer.append(URLEncoder.encode(value+"", "UTF-8"));
                    buffer.append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        }
        String str = buffer.toString();
        //去掉最后的&
        if (str.length() > 1 && str.endsWith("&")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 构建一个GsonRequest
     */
    private static Request makeGsonRequest(int method, String url, Map<String, Object> params, Class<? extends RBResponse> clazz, int requestCode, ResponseListener listener, boolean isCache) {
        RequestListener httpListener = new RequestListener(listener, requestCode);
        GsonRequest gsonRequest = new GsonRequest<RBResponse>(method, url, params, clazz, httpListener, httpListener, isCache) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //TODO 默认处理，如需自定义header，可重写
                return generateHeaders();
            }
        };
        gsonRequest.setRetryPolicy(new DefaultRetryPolicy());//设置默认的重试机制，超时时间，重试次数，重试因子等
        return gsonRequest;
    }
    /**
     * 生成公共Header头信息
     * @return
     */
    public static Map<String, String> generateHeaders() {
        mHeaderList= MainActivity.mListHeader();
        Map<String, String> headers = new HashMap<String, String>();
        //        appkey        软件身份key
        //        udid          手机客户端的唯一标识
        //        os            操作系统名称
        //        osversion     操作系统版本
        //        appversion    APP版本
        //        sourceid      推广ID
        //        ver           通讯协议版本
        //        userid        用户ID
        //        usersession   登陆后得到的用户唯一性标识
        //        unique        激活后得到的设备唯一性标识

                     /**新增头*/
            //userid         用户id 登录成功后，接口返回的userId
           //platformtype:   接口调用平台的类型    android
           //calltime:       接口本地调用时间戳    400
           //mobilebrand:    手机型号        String mtype = android.os.Build.MODEL; // 手机型号
          //appversionno:    APP版本号   	PackageInfo packages=context.getPackageManager().getPackageInfo(context.getPackageName(), 0); // String version=packages.versionName;
          //tokenid: 64a2979d-2e05-4351-9983-55dee47c98b0  登录成功后，接口返回的Token

          //Cookie: JSESSIONID=76195ab6-7df9-4901-a6eb-fcbced9bd0dd
         //sign: 23c196f8677c55de5abc9a70ebb12bd0  将所有的参数进行加密计算，得到的接口签名
           headers.put("userid",mHeaderList.get(1));
           headers.put("tokenid",mHeaderList.get(0));
           headers.put("platformtype","android");
           headers.put("calltime","400");
           headers.put("mobilebrand",mHeaderList.get(2));
           headers.put("appversionno","1.0");
           headers.put("sign",mHeaderList.get(3));
           headers.put("Cookie", "JSESSIONID=" +mHeaderList.get(0));
           headers.put("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
        return headers;
    }
    /**
     * 成功获取到服务器响应结果的监听，供UI层调用
     */
    public interface ResponseListener {

        /**
         * 成功获取到服务器响应数据的时候调用
         * @param requestCode   response对应的requestCode
         * @param response      返回的response
         */
        void onGetResponseSuccess(int requestCode, RBResponse response);

        /**
         * 网络获取失败，(做一些释放性的操作，比如关闭对话框)
         * @param requestCode   请求码
         * @param error         错误信息
         */
        void onGetResponseError(int requestCode, VolleyError error);
    }

    /**
     * 对Volley请求的两种监听的封装，并执行一些默认操作，结果抛给供UI层注册的ResponseListener
     */
    private static class RequestListener implements Response.Listener<RBResponse>, Response.ErrorListener {

        private ResponseListener listener;
        private int requestCode;

        public RequestListener(ResponseListener listener, int requestCode) {
            this.listener = listener;
            this.requestCode = requestCode;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            ALog.w("----Request error from network!");
            error.printStackTrace();

            //请求错误，从正在飞的集合中删除该请求
            mInFlightRequests.remove(requestCode);
            if (listener != null) {
                listener.onGetResponseError(requestCode, error);
            }
        }
        @Override
        public void onResponse(RBResponse response) {
            ALog.w("----onResponse from network!");
            mInFlightRequests.remove(requestCode);
            if (response != null) {
                //执行通用处理，如果是服务器返回的ErrorResponse，直接提示错误信息并返回
                if ("error".equals(response.getResponse()) && response instanceof ErrorResponse) {
                    ErrorResponse errorResponse = (ErrorResponse) response;
                    MyToast.show(App.application, errorResponse.getError().getText());
                    return;
                }
                if (listener != null) {
                    listener.onGetResponseSuccess(requestCode, response);
                }
            }
        }
    }
}
