package com.minhr.design.module_core.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
class MainTabEntity (var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int) : CustomTabEntity {
    override fun getTabUnselectedIcon(): Int  = unSelectedIcon

    override fun getTabSelectedIcon(): Int  = selectedIcon

    override fun getTabTitle(): String  = title
}