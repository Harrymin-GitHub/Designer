package com.minhr.design.module_mall.mvp.presenter

import com.minhr.design.common_base.dagger.mvp.BasePresenter
import com.minhr.design.common_base.net.RetrofitManager
import com.minhr.design.common_base.net.RxObserverListener
import com.minhr.design.module_mall.bean.CategoryBean
import com.minhr.design.module_mall.bean.FilterBean
import com.minhr.design.module_mall.bean.RecordsEntity
import com.minhr.design.module_mall.bean.SearchMockData
import com.minhr.design.module_mall.mvp.contract.SearchContract
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 搜索
 */
class SearchPresenter @Inject constructor() : BasePresenter<SearchContract.View, SearchContract.Model>(), SearchContract.Presenter {
    override fun getHotList() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getHotList(), object : RxObserverListener<List<String>>(mView) {
            override fun onSuccess(result: List<String>?) {
                val list = if (result.isNullOrEmpty()) SearchMockData.hotList() else result
                mView?.getHotList(list)
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                // api.xiangqu.com 已下线，用本地热门词保证搜索页可演示
                mView?.dismissDialogLoading()
                mView?.getHotList(SearchMockData.hotList())
            }
        }))
    }

    override fun getSearchGoods(outCategoryId: String, keyword: String, page: Int, sort: Int, queryParams: Map<String, String>) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getSearchGoods(outCategoryId, keyword, page, sort, queryParams), object : RxObserverListener<RecordsEntity>(mView) {
            override fun onSuccess(result: RecordsEntity?) {
                val data = if (result == null || result.records.isNullOrEmpty()) {
                    SearchMockData.searchGoods(keyword)
                } else {
                    result
                }
                mView?.getSearchGoods(data)
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                mView?.dismissDialogLoading()
                mView?.getSearchGoods(SearchMockData.searchGoods(keyword))
            }
        }))
    }

    override fun getCategoryList(outCategoryId: String, keyword: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getCategoryList(outCategoryId, keyword), object : RxObserverListener<List<CategoryBean>>(mView) {
            override fun onSuccess(result: List<CategoryBean>?) {
                val list = if (result.isNullOrEmpty()) SearchMockData.categoryList() else result
                mView?.getCategoryList(list)
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                mView?.dismissDialogLoading()
                mView?.getCategoryList(SearchMockData.categoryList())
            }
        }))
    }

    override fun getFilterData(outCategoryId: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getFilterData(outCategoryId), object : RxObserverListener<FilterBean>(mView) {
            override fun onSuccess(result: FilterBean?) {
                mView?.getFilterData(result ?: SearchMockData.filterData())
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                mView?.dismissDialogLoading()
                mView?.getFilterData(SearchMockData.filterData())
            }
        }))
    }
}