package com.minhr.design.module_mall.mvp.model

import com.minhr.design.module_mall.bean.CategoryBean
import com.minhr.design.module_mall.bean.FilterBean
import com.minhr.design.module_mall.bean.RecordsEntity
import com.minhr.design.module_mall.mvp.contract.SearchContract
import com.minhr.design.module_mall.net.NetMallProvider
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 搜索商品
 */
class SearchModel @Inject constructor() : SearchContract.Model {
    override fun getHotList(): Observable<BaseHttpResponse<List<String>>> = NetMallProvider.requestService.getHotList()

    override fun getSearchGoods(outCategoryId: String, keyword: String, page: Int, sort: Int, queryParams: Map<String, String>): Observable<BaseHttpResponse<RecordsEntity>> = NetMallProvider.requestService.getSearchGoods(outCategoryId, keyword, page, sort, queryParams)

    override fun getCategoryList(outCategoryId: String, keyword: String): Observable<BaseHttpResponse<List<CategoryBean>>> = NetMallProvider.requestService.getCategoryList(outCategoryId, keyword)

    override fun getFilterData(outCategoryId: String): Observable<BaseHttpResponse<FilterBean>> = NetMallProvider.requestService.getFilterData(outCategoryId)

}