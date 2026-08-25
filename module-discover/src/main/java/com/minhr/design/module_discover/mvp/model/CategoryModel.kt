package com.minhr.design.module_core.mvp.model

import com.minhr.design.module_core.mvp.contract.CategoryContract
import com.minhr.design.module_core.net.NetFoundProvider
import com.minhr.design.module_discover.bean.CategoryBean
import com.minhr.design.module_discover.bean.ItemEntity
import com.minhr.design.module_discover.bean.TabEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 分类
 */
class CategoryModel @Inject constructor() : CategoryContract.Model {
    override fun getCategoryTabs(id: String): Observable<TabEntity> = NetFoundProvider.requestService.getCategoryTabs(id)

    override fun getCategories(): Observable<List<CategoryBean>> = NetFoundProvider.requestService.getCategories()

    override fun getCategorieDetail(id: String, tabType: Int): Observable<ItemEntity> {
        var observable: Observable<ItemEntity>? = null
        when (tabType) {
            0, 1 -> observable = NetFoundProvider.requestService.getCategorieDetail(id)
            2 -> observable = NetFoundProvider.requestService.getCategoryAuthor(id)
            3 -> observable = NetFoundProvider.requestService.getCategoryPlaylist(id)

        }
        return observable!!
    }
}