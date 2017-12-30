package com.hhzt.vod.api;

/**
 * Created by wujichang on 2017/12/29.
 */

public interface IHttpRetCallBack<T> {

    void onResponseSuccess(CommonRspRetBean bean, T t);

    void onResponseFailed(CommonRspRetBean bean);

    void onError(String result);

    void onCancelled();

    void onFinish();
}
