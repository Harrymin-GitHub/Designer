package com.minhr.design.module_core.mvp.model

import com.minhr.design.module_core.bean.TopicBean
import com.minhr.design.module_core.bean.TopicDetailEntity
import com.minhr.design.module_core.mvp.contract.TopicContract
import com.minhr.design.module_core.net.NetCoreProvider
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 专题
 */
class TopicModel @Inject constructor() : TopicContract.Model {
    override fun getTopics(id: String): Observable<BaseHttpResponse<List<TopicBean>>> = NetCoreProvider.requestService.getTopics(id)

    override fun getTopicDetail(id: String): Observable<BaseHttpResponse<TopicDetailEntity>> = NetCoreProvider.requestService.getTopicDetail(id)

}