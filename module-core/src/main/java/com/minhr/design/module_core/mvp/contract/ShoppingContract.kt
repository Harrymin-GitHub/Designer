package com.minhr.design.module_core.mvp.contract

import com.minhr.design.common_base.dagger.mvp.BaseContract
import com.minhr.design.common_base.net.BaseResponse
import com.minhr.design.module_core.bean.*
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 逛
 */
interface ShoppingContract {
    interface View : BaseContract.BaseView {
        fun getCategoryList(dataList: List<CategoryEntity>)
        fun getGoodsList(dataList: List<GoodsEntity>)
        fun getHandPickedGoods(bean: RecordsEntity)
        fun getPersonLike(dataList: List<AllfaverEntity>)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getCategoryList()
        fun getGoodsList()
        fun getHandPickedGoods(page: String)
        fun getPersonLike()
    }

    interface Model : BaseContract.BaseModel {
        fun getCategoryList(): Observable<BaseHttpResponse<List<CategoryEntity>>>
        fun getGoodsList(): Observable<BaseResponse<List<GoodsEntity>>>
        fun getHandPickedGoods(page: String): Observable<BaseHttpResponse<RecordsEntity>>
        fun getPersonLike(): Observable<BaseHttpResponse<List<AllfaverEntity>>>
    }
}