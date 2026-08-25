package com.minhr.design.module_test.dagger22

import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
class A @Inject constructor(private val aa: Aa, private val aaa: Aaa) {
    fun getData() = aa.getData() + aaa.getData()
}