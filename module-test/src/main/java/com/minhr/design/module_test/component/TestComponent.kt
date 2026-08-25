package com.minhr.design.module_test.component

import com.minhr.design.module_test.LoginActivity
import com.will.weiyuekotlin.component.ApplicationComponent
import dagger.Component

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
@Component(dependencies = [(ApplicationComponent::class)])
interface TestComponent {
    fun inject(activity: LoginActivity)
}