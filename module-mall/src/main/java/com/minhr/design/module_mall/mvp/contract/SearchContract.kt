package com.minhr.design.module_mall.mvp.contract

import com.minhr.design.common_base.dagger.mvp.BaseContract
import com.minhr.design.module_mall.bean.CategoryBean
import com.minhr.design.module_mall.bean.FilterBean
import com.minhr.design.module_mall.bean.RecordsEntity
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 搜索商品
 */
interface SearchContract {
    interface View : BaseContract.BaseView {
        fun getHotList(dataList: List<String>)
        fun getSearchGoods(dataBean: RecordsEntity)
        fun getCategoryList(dataList: List<CategoryBean>)
        fun getFilterData(dataBean: FilterBean)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getHotList()
        fun getSearchGoods(outCategoryId: String, keyword: String, page: Int, sort: Int, queryParams: Map<String, String>)
        fun getCategoryList(outCategoryId: String, keyword: String)
        fun getFilterData(outCategoryId: String)
    }

    interface Model : BaseContract.BaseModel {
        fun getHotList(): Observable<BaseHttpResponse<List<String>>>
        fun getSearchGoods(outCategoryId: String, keyword: String, page: Int, sort: Int, queryParams: Map<String, String>): Observable<BaseHttpResponse<RecordsEntity>>
        fun getCategoryList(outCategoryId: String, keyword: String): Observable<BaseHttpResponse<List<CategoryBean>>>
        fun getFilterData(outCategoryId: String): Observable<BaseHttpResponse<FilterBean>>
    }
}