package com.minhr.design.module_core.mvp.contract

import com.minhr.design.common_base.dagger.mvp.BaseContract
import com.minhr.design.module_core.bean.TopicBean
import com.minhr.design.module_core.bean.TopicDetailEntity
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 专题
 */
interface TopicContract {
    interface View : BaseContract.BaseView {
        fun getTopics(dataList: List<TopicBean>)
        fun getTopicDetail(dataBean: TopicDetailEntity)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getTopics(id: String)
        fun getTopicDetail(id: String)
    }

    interface Model : BaseContract.BaseModel {
        fun getTopics(id: String): Observable<BaseHttpResponse<List<TopicBean>>>
        fun getTopicDetail(id: String): Observable<BaseHttpResponse<TopicDetailEntity>>
    }
}