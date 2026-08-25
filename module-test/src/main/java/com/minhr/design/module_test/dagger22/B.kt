package com.minhr.design.module_test.dagger22

import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
class B {
    @Inject lateinit var a: A

    init {
        DaggerABComponent.create().Inject(this)
    }

    fun getData() = a.getData()
}
