package com.minhr.design.module_core.mvp.presenter

import com.minhr.design.common_base.dagger.mvp.BasePresenter
import com.minhr.design.common_base.net.RetrofitManager
import com.minhr.design.common_base.net.RxObserverListener
import com.minhr.design.module_core.bean.*
import com.minhr.design.module_core.mvp.contract.ShoppingContract
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 逛
 */
class ShoppingPresenter @Inject constructor() : BasePresenter<ShoppingContract.View, ShoppingContract.Model>(), ShoppingContract.Presenter {
    override fun getCategoryList() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getCategoryList(), object : RxObserverListener<List<CategoryEntity>>(mView) {
            override fun onSuccess(result: List<CategoryEntity>?) {
                val list = if (result.isNullOrEmpty()) ShoppingMockData.categoryList() else result
                mView?.getCategoryList(list)
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                // api.xiangqu.com 已下线，用本地数据保证逛页可演示
                mView?.dismissDialogLoading()
                mView?.getCategoryList(ShoppingMockData.categoryList())
            }
        }))
    }

    override fun getGoodsList() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doRequestOther(mModel!!.getGoodsList(), object : RxObserverListener<List<GoodsEntity>>(mView) {
            override fun onSuccess(result: List<GoodsEntity>?) {
                val list = if (result.isNullOrEmpty()) ShoppingMockData.goodsList() else result
                mView?.getGoodsList(list)
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                mView?.dismissDialogLoading()
                mView?.getGoodsList(ShoppingMockData.goodsList())
            }
        }))
    }

    override fun getHandPickedGoods(page: String) {
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getHandPickedGoods(page), object : RxObserverListener<RecordsEntity>(mView) {
            override fun onSuccess(result: RecordsEntity?) {
                mView?.getHandPickedGoods(result ?: ShoppingMockData.handPickedGoods())
            }

            override fun onError(e: Throwable) {
                mView?.getHandPickedGoods(ShoppingMockData.handPickedGoods())
            }
        }))
    }

    override fun getPersonLike() {
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getPersonLike(), object : RxObserverListener<List<AllfaverEntity>>(mView) {
            override fun onSuccess(result: List<AllfaverEntity>?) {
                val list = if (result.isNullOrEmpty()) ShoppingMockData.personLike() else result
                mView?.getPersonLike(list)
            }

            override fun onError(e: Throwable) {
                mView?.getPersonLike(ShoppingMockData.personLike())
            }
        }))
    }
}