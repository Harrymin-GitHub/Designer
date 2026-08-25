package com.minhr.design.module_core.mvp.model

import com.minhr.design.common_base.net.BaseResponse
import com.minhr.design.module_core.bean.*
import com.minhr.design.module_core.mvp.contract.ShoppingContract
import com.minhr.design.module_core.net.NetCoreProvider
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 逛
 */
class ShoppingModel @Inject constructor() : ShoppingContract.Model {
    //商品分类
    override fun getCategoryList(): Observable<BaseHttpResponse<List<CategoryEntity>>> = NetCoreProvider.requestService.getCategoryList()
    //商品列表
    override fun getGoodsList(): Observable<BaseResponse<List<GoodsEntity>>> = NetCoreProvider.requestService.getGoodsList()
    //精选
    override fun getHandPickedGoods(page: String): Observable<BaseHttpResponse<RecordsEntity>> = NetCoreProvider.requestService.getHandPickedGoods(page)
    //大家喜欢
    override fun getPersonLike(): Observable<BaseHttpResponse<List<AllfaverEntity>>> = NetCoreProvider.requestService.getPersonLike()
}