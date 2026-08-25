package com.minhr.design.common_base.net

import java.lang.reflect.ParameterizedType

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Base NetServiceProvider通过每个module传入具体的业务接口类型服务实例化ApiService
 */
class NetServiceProvider<T> {

    fun getNetService(): T {
        val tClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        return RetrofitManager.getRetrofit().create(tClass)
    }
}
