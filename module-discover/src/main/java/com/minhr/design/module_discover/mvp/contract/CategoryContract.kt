package com.minhr.design.module_core.mvp.contract

import com.minhr.design.common_base.dagger.mvp.BaseContract
import com.minhr.design.module_discover.bean.CategoryBean
import com.minhr.design.module_discover.bean.ItemEntity
import com.minhr.design.module_discover.bean.TabEntity
import io.reactivex.Observable

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 分类
 */
interface CategoryContract {
    interface View : BaseContract.BaseView {
        fun getCategoryTabs(dataBean: TabEntity)
        fun getCategories(dataList: List<CategoryBean>)
        fun getCategorieDetail(dataBean: ItemEntity)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getCategoryTabs(id: String)
        fun getCategories()
        //tabType为滑动的tab类型
        fun getCategorieDetail(id: String, tabType: Int)
    }

    interface Model : BaseContract.BaseModel {
        fun getCategoryTabs(id: String): Observable<TabEntity>
        fun getCategories(): Observable<List<CategoryBean>>
        fun getCategorieDetail(id: String, tabType: Int): Observable<ItemEntity>
    }
}