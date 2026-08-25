package com.minhr.design.module_mall.net

import com.minhr.design.common_base.net.RetrofitManager

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 单例提供Retrofit请求的Service
 */
object NetMallProvider {

    val requestService: ApiMallService
        get() = RetrofitManager.getRetrofit().create<ApiMallService>(ApiMallService::class.java)
}