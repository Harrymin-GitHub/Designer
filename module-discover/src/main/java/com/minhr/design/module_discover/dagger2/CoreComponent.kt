package com.minhr.design.module_core.dagger2

import com.minhr.design.module_discover.ui.ACT_Category
import com.minhr.design.module_discover.ui.ACT_CategoryDetail
import com.minhr.design.module_discover.ui.FRA_CategoryDetail
import com.will.weiyuekotlin.component.ApplicationComponent
import dagger.Component

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : Dagger2
 */
@Component(dependencies = [(ApplicationComponent::class)])
interface FoundComponent {
    fun inject(activity: ACT_Category)
    fun inject(activity: ACT_CategoryDetail)
    fun inject(activity: FRA_CategoryDetail)
}