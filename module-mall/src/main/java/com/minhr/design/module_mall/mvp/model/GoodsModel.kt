package com.minhr.design.module_mall.mvp.model

import com.minhr.design.module_mall.bean.*
import com.minhr.design.module_mall.mvp.contract.GoodsContract
import com.minhr.design.module_mall.mvp.contract.SearchContract
import com.minhr.design.module_mall.net.NetMallProvider
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品详情
 */
class GoodsModel @Inject constructor() : GoodsContract.Model {
    override fun getGoodsContent(productId: String): Observable<BaseHttpResponse<GoodsContentBean>> = NetMallProvider.requestService.getGoodsContent(productId)

    override fun getGoodsDescription(productId: String): Observable<BaseHttpResponse<List<GoodsDesBean>>> = NetMallProvider.requestService.getGoodsDescription(productId)

    override fun getGoodsCommentList(productId: String, type: Int): Observable<BaseHttpResponse<List<CommentBean>>> = NetMallProvider.requestService.getGoodsCommentList(productId, type)

    override fun getRevelentGoodsList(productId: String): Observable<BaseHttpResponse<RevelentBean>> = NetMallProvider.requestService.getRevelentGoodsList(productId)

}