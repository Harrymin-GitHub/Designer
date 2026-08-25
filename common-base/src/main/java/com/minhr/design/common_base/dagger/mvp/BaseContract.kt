package com.minhr.design.common_base.dagger.mvp

import com.minhr.design.common_base.bean.ErrorBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Dagger2_MVP 契约类-管理MVP层接口
 */
interface BaseContract {
    interface BaseView {
        fun showLoading()

        fun showDialogLoading(msg: String)

        fun dismissDialogLoading()

        fun showBusinessError(error: ErrorBean)

        fun showException(error: ErrorBean)
    }

    interface BasePresenter {

        /**
         * 绑定View
         */
        fun attachViewModel(view: BaseView,model: BaseContract.BaseModel)

        /**
         * 解除绑定
         */
        fun detachView()

        fun onDestroy()
    }

    interface BaseModel {
    }
}