package com.minhr.design.module_core.mvp.model

import com.minhr.design.module_core.mvp.contract.TestContract
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Dagger2_MVP-Model
 */
class TestModel @Inject constructor() : TestContract.Model {
    override fun getData(): String = "Dagger2+MVP测试成功咯"
}