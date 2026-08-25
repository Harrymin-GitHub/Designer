package com.minhr.design.module_core.mvp.presenter

import com.minhr.design.common_base.dagger.mvp.BasePresenter
import com.minhr.design.common_base.net.RetrofitManager
import com.minhr.design.common_base.net.RxObserverListener
import com.minhr.design.module_core.bean.TopicBean
import com.minhr.design.module_core.bean.TopicDetailEntity
import com.minhr.design.module_core.bean.TopicMockData
import com.minhr.design.module_core.mvp.contract.TopicContract
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 专题
 */
class TopicPresenter @Inject constructor() : BasePresenter<TopicContract.View, TopicContract.Model>(), TopicContract.Presenter {
    override fun getTopics(id: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getTopics(id), object : RxObserverListener<List<TopicBean>>(mView) {
            override fun onSuccess(result: List<TopicBean>?) {
                val list = if (result.isNullOrEmpty()) TopicMockData.topics() else result
                mView?.getTopics(list)
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                // api.xiangqu.com 已下线，用本地数据保证专题页可演示
                mView?.dismissDialogLoading()
                mView?.getTopics(TopicMockData.topics())
            }
        }))
    }

    override fun getTopicDetail(id: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getTopicDetail(id), object : RxObserverListener<TopicDetailEntity>(mView) {
            override fun onSuccess(result: TopicDetailEntity?) {
                mView?.getTopicDetail(result ?: TopicMockData.topicDetail(id))
                mView?.dismissDialogLoading()
            }

            override fun onError(e: Throwable) {
                mView?.dismissDialogLoading()
                mView?.getTopicDetail(TopicMockData.topicDetail(id))
            }
        }))
    }
}