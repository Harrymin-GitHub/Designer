package com.minhr.design.module_core.mvp.presenter

import android.util.Log
import com.minhr.design.common_base.dagger.mvp.BasePresenter
import com.minhr.design.common_base.net.RetrofitManager
import com.minhr.design.common_base.net.RxObserverListener
import com.minhr.design.module_core.mvp.contract.CategoryContract
import com.minhr.design.module_discover.bean.CategoryBean
import com.minhr.design.module_discover.bean.ItemEntity
import com.minhr.design.module_discover.bean.TabEntity
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 分类
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryContract.View, CategoryContract.Model>(), CategoryContract.Presenter {
    override fun getCategoryTabs(id: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doRequest(mModel!!.getCategoryTabs(id), object : RxObserverListener<TabEntity>(mView) {
            override fun onSuccess(result: TabEntity?) {
                mView?.getCategoryTabs(result!!)
                mView?.dismissDialogLoading()
            }

        }))
    }

    override fun getCategories() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doRequest(mModel!!.getCategories(), object : RxObserverListener<List<CategoryBean>>(mView) {
            override fun onSuccess(result: List<CategoryBean>?) {
                mView?.getCategories(result!!)
                mView?.dismissDialogLoading()
            }

        }))
    }

    override fun getCategorieDetail(id: String, tabType: Int) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doRequest(mModel!!.getCategorieDetail(id, tabType), object : RxObserverListener<ItemEntity>(mView) {
            override fun onSuccess(result: ItemEntity?) {
                mView?.getCategorieDetail(result!!)
                mView?.dismissDialogLoading()
            }

        }))
    }
}