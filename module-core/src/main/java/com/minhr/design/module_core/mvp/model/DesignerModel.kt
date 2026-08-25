package com.minhr.design.module_core.mvp.model

import com.minhr.design.common_base.net.BaseResponse
import com.minhr.design.module_core.net.NetCoreProvider
import com.minhr.design.module_core.bean.DesignerEntity
import com.minhr.design.module_core.bean.TagCategoryEntity
import com.minhr.design.module_core.mvp.contract.DesignerContract
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 设计师 model
 */
class DesignerModel @Inject constructor() : DesignerContract.Model {
    /**
     * 获取推荐设计师
     */
    override fun getRecommendDesigner(): Observable<BaseHttpResponse<DesignerEntity>> = NetCoreProvider.requestService.getRecommendDesigner()

    /**
     * 获取涉及标签类型列表
     */
    override fun getDesignerTypeList(): Observable<BaseHttpResponse<List<TagCategoryEntity>>> = NetCoreProvider.requestService.getDesignerTypeList()

    /**
     * 获取设计师列表
     */
    override fun getDesinerList(tagCategoryId: String, tagId: String): Observable<BaseResponse<List<DesignerEntity>>> = NetCoreProvider.requestService.getDesignerList(tagCategoryId, tagId)
}