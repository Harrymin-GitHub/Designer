package com.minhr.design.common_base.net

import com.minhr.design.common_base.bean.DataBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 数据格式统一封装(data下嵌套了json)
 */
class BaseResponse<T>(code: Int, msg: String, data: T) {
    var code: Int = 0
    var msg: String? = null
    var success: Boolean = false
    var data: DataBean<T>? = null
}