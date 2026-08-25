package com.minhr.design.module_core.dagger2

import com.minhr.design.module_core.ui.designer.ACT_DesignerList
import com.minhr.design.module_core.ui.home.*
import com.minhr.design.module_core.ui.test.TestDaggerActivity
import com.minhr.design.module_core.ui.test.TestFragment
import com.minhr.design.module_core.ui.test.TestMVPActivity
import com.will.weiyuekotlin.component.ApplicationComponent
import dagger.Component

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
@Component(dependencies = [(ApplicationComponent::class)])
interface CoreComponent {
    fun inject(activity: TestMVPActivity)
    fun inject(activity: TestDaggerActivity)
    fun inject(fragment: TestFragment)
    fun inject(fragment: DesignerFragment)
    fun inject(fragment: ShoppingFragmentOld)
    fun inject(fragment: ShoppingFragment)
    fun inject(fragment: TopicFragment)
    fun inject(fragment: HandpickedFragment)
    fun inject(fragment: AllFavorFragment)
    fun inject(activity: ACT_DesignerList)
}