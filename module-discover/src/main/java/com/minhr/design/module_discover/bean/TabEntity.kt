package com.minhr.design.module_discover.bean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
data class TabEntity(var tabInfo: TabInfoEntity) {
    data class TabInfoEntity(var tabList: List<TabBean>) {
        data class TabBean(var id: String, var name: String, var apiUrl: String)
    }
}