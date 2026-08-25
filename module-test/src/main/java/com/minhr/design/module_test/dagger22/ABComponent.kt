package com.minhr.design.module_test.dagger22

import dagger.Component

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
@Component
interface ABComponent {
    fun Inject(b: B)
}