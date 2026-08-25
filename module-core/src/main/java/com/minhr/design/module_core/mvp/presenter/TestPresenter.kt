package com.minhr.design.module_core.mvp.presenter

import android.util.Log
import com.minhr.design.common_base.dagger.mvp.BasePresenter
import com.minhr.design.module_core.mvp.contract.TestContract
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Dagger2_MVP-Presenter
 */
class TestPresenter @Inject constructor() : BasePresenter<TestContract.View, TestContract.Model>(), TestContract.Presenter {
    override fun getData(text: String) {
        Log.e("TAG", "TestPresenter" + text)
        mView?.getData(mModel?.getData() + "")
        Log.e("TAG", "   mView?.getData()")
    }
}