package com.minhr.design.module_test

import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
class PresenterImpl @Inject constructor() : MainPresenter {
    override fun getData(): String {
        return "哈哈哈哈哈"
    }
}