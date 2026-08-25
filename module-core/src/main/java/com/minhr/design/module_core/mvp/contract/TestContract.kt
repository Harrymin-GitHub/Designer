package com.minhr.design.module_core.mvp.contract

import com.minhr.design.common_base.dagger.mvp.BaseContract

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Dagger2_MVP-契约类：管理MVP
 */
interface TestContract {
    interface View : BaseContract.BaseView {
        fun getData(result: String)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getData(text: String)
    }

    interface Model : BaseContract.BaseModel {
        fun getData():String
    }
}