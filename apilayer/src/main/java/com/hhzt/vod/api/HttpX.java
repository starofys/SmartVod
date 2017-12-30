package com.hhzt.vod.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by wujichang on 2017/12/28.
 */

public class HttpX {

    private static final String TAG_HTTP_API = "HttpX---->";

    private static final int CODE_NULL = -1;

    private HttpX() {
    }

    /**
     * get  String
     *
     * @param url
     * @param iHttpRetCallBack
     */
    public static <T> void get(final String url,
                               final String body,
                               final IHttpRetCallBack<T> iHttpRetCallBack,
                               final Class<T> tClass,
                               final String tag) {
        RequestParams params = new RequestParams(url);
        params.setMethod(HttpMethod.GET);
        params.setCacheMaxAge(0);

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (null != iHttpRetCallBack) {
                    checkResult(result, url, body, iHttpRetCallBack, tClass, tag, true);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (null != iHttpRetCallBack) {
                    iHttpRetCallBack.onError(ex.getMessage());
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
                if (null != iHttpRetCallBack) {
                    iHttpRetCallBack.onCancelled();
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static <T> void post(final String url,
                                final String body,
                                final IHttpRetCallBack<T> iHttpRetCallBack,
                                final Class<T> tClass,
                                final String tag) {
        post(url, body, iHttpRetCallBack, tClass, tag, false);
    }


    /**
     * post  String
     *
     * @param url
     * @param body
     * @param iHttpRetCallBack
     */
    public static <T> void post(final String url,
                                final String body,
                                final IHttpRetCallBack<T> iHttpRetCallBack,
                                final Class<T> tClass,
                                final String tag,
                                final boolean isShowToastOnSuccess) {

        RequestParams params = new RequestParams(url);
        params.setMethod(HttpMethod.POST);
        params.setCacheMaxAge(0);
        params.setBodyContent(body);

        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LogUtil.d(TAG_HTTP_API + "onSuccess : " + result);
                if (null != iHttpRetCallBack) {
                    checkResult(result, url, body, iHttpRetCallBack, tClass, tag, false);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.w(TAG_HTTP_API + "onError : " + ex.toString());
                if (null != iHttpRetCallBack) {
                    iHttpRetCallBack.onError(ex.getMessage());
                }
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.d(TAG_HTTP_API + "onCancelled : " + cex.getMessage());
                if (null != iHttpRetCallBack) {
                    iHttpRetCallBack.onCancelled();
                }
            }

            @Override
            public void onFinished() {
                LogUtil.d(TAG_HTTP_API + "onFinished");
                if (null != iHttpRetCallBack) {
                    iHttpRetCallBack.onFinish();
                }
            }
        });
    }

    private static <T> void checkResult(String result,
                                        final String url,
                                        final String body,
                                        final IHttpRetCallBack<T> iHttpRetCallBack,
                                        final Class<T> tClass,
                                        final String tag,
                                        final boolean isShowResult) {
        LogUtil.d(TAG_HTTP_API + "checkResult : \r\n url = " + url + "\r\n" + "body = " + body);

        CommonRspRetBean bean = CommonRspRetBean.fromString(result);
        if (bean == null) {
            bean = new CommonRspRetBean();
            bean.code = CODE_NULL;
        }

        LogUtil.d(tag + bean.toString());
        if (bean.isSuccess()) {
            if (tClass == null) {
                iHttpRetCallBack.onResponseSuccess(bean, null);
            } else {
                iHttpRetCallBack.onResponseSuccess(bean, TypeUtils.castToJavaBean(bean.data, tClass));
            }
        } else {
            iHttpRetCallBack.onResponseFailed(bean);
        }
    }

    private static String obtainNewBody(String body, String newToken) {
        JSONObject jsonObject = JSON.parseObject(body);
        jsonObject.put("token", newToken);
        return jsonObject.toString();
    }

    /**
     * 与web端的交互
     */

    public static void post2Web(String url, String body, final Callback.CommonCallback<String> callback) {


        RequestParams params = new RequestParams(url);
        params.setMethod(HttpMethod.POST);
        params.setCacheMaxAge(0);
        params.setBodyContent(body);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.d(TAG_HTTP_API + "onSuccess : " + result);
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.d(TAG_HTTP_API + "onError : " + ex.getMessage());
                if (callback != null) {
                    callback.onError(ex, isOnCallback);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.d(TAG_HTTP_API + "onCancelled : " + cex.getMessage());
                if (callback != null) {
                    callback.onCancelled(cex);
                }
            }

            @Override
            public void onFinished() {
                LogUtil.d(TAG_HTTP_API + "onFinished");
                if (callback != null) {
                    callback.onFinished();
                }
            }
        });

    }

}
