package com.minhr.design.module_test.dagger22

import android.util.Log
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
class Aa @Inject constructor() {
    init {
        Log.e("TAG","Aa类初始化后，")

    }
    fun getData() = "在kotlin中使用注解构造方法的方式拿到值"
}