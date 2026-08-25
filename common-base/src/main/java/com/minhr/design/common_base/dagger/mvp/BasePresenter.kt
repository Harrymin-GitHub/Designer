package com.minhr.design.common_base.dagger.mvp

import com.minhr.design.common_base.net.RxManager

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Dagger2_MVP -BasePresenter
 */
open abstract class BasePresenter<V : BaseContract.BaseView, M : BaseContract.BaseModel> : BaseContract.BasePresenter {
    var mView: V? = null
    var mModel: M? = null
    var rxManager: RxManager? = RxManager()

    override fun attachViewModel(view: BaseContract.BaseView, model: BaseContract.BaseModel) {
        mView = view as V
        mModel = model as M
    }

    override fun detachView() {
        mView?.let { mView = null }
        mModel?.let { mModel = null }
    }

    override fun onDestroy() {
        rxManager!!.clear()
        rxManager = null
    }

}