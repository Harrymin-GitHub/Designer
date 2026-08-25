package com.minhr.design.module_mall.dagger2

import com.minhr.design.module_mall.ui.ACT_GoodsDetail
import com.minhr.design.module_mall.ui.ACT_GoodsFilter
import com.minhr.design.module_mall.ui.ACT_Search
import com.minhr.design.module_mall.ui.FRA_GoodsComment
import com.will.weiyuekotlin.component.ApplicationComponent
import dagger.Component

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
@Component(dependencies = [(ApplicationComponent::class)])
interface MallComponent {
    fun inject(activity: ACT_Search)
    fun inject(activity: ACT_GoodsFilter)
    fun inject(activity: ACT_GoodsDetail)
    fun inject(activity: FRA_GoodsComment)
}