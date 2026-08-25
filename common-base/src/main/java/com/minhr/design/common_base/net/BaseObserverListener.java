package com.minhr.design.common_base.net;


import com.minhr.design.common_base.bean.ErrorBean;
/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : description：自定义接口，处理联网请求结果的回调
 */
public interface BaseObserverListener<T> {
    void onSuccess(T result);
    void onComplete();
    void onError(Throwable e);
    void onBusinessError(ErrorBean errorBean);
}
