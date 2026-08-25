package com.minhr.design.common_base.bean

/**
 * author : Harrymin
 * e-mail : minhongrui@126.com
 * date   : 2026/8/25 7:38 PM
 * desc   :
 */
class DataBean<T> {
    var content: T? = null
    var numberOfElements: Int = 0
    var size: Int = 0
    var totalElements: Int = 0
    var totalPages: Int = 0
}
