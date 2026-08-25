package com.minhr.design.module_test.dagger22

import android.util.Log
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
class Aaa @Inject constructor(private val aa: Aa) {
    init {
        Log.e("TAG","Aaa类初始化后，")
    }

    fun getData() = "在Aaa中获取到Aa的数据:" + aa.getData()
}