package com.minhr.design.module_core.mvp.presenter

import com.minhr.design.common_base.dagger.mvp.BasePresenter
import com.minhr.design.common_base.net.RetrofitManager
import com.minhr.design.common_base.net.RxObserverListener
import com.minhr.design.module_core.bean.DesignerEntity
import com.minhr.design.module_core.bean.DesignerMockData
import com.minhr.design.module_core.bean.TagCategoryEntity
import com.minhr.design.module_core.mvp.contract.DesignerContract
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 设计师 presenter
 */
class DesignerPresenter @Inject constructor() : BasePresenter<DesignerContract.View, DesignerContract.Model>(), DesignerContract.Presenter {
    override fun getDesinerList(tagCategoryId: String, tagId: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doRequestOther(mModel!!.getDesinerList(tagCategoryId, tagId), object : RxObserverListener<List<DesignerEntity>>(mView) {
            override fun onSuccess(result: List<DesignerEntity>?) {
                val list = if (result.isNullOrEmpty()) DesignerMockData.designerList() else result
                mView?.getDesinerList(list)
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                mView?.dismissDialogLoading()
                mView?.getDesinerList(DesignerMockData.designerList())
            }
        }))
    }

    override fun getRecommendDesigner() {
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getRecommendDesigner(), object : RxObserverListener<DesignerEntity>(mView) {
            override fun onSuccess(result: DesignerEntity?) {
                mView?.getRecommendDesigner(result ?: DesignerMockData.recommendDesigner())
            }

            override fun onError(e: Throwable) {
                mView?.getRecommendDesigner(DesignerMockData.recommendDesigner())
            }
        }))
    }

    override fun getDesignerTypeList() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getDesignerTypeList(), object : RxObserverListener<List<TagCategoryEntity>>(mView) {
            override fun onSuccess(result: List<TagCategoryEntity>?) {
                val list = if (result.isNullOrEmpty()) DesignerMockData.typeList() else result
                mView?.getDesignerTypeList(list)
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                // api.xiangqu.com 已下线，用本地数据保证设计师页可演示
                mView?.dismissDialogLoading()
                mView?.getDesignerTypeList(DesignerMockData.typeList())
            }
        }))
    }
}
