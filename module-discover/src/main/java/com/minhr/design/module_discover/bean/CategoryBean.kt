package com.minhr.design.module_discover.bean

import java.io.Serializable

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 视频分类
 */
data class CategoryBean(var id: String, var name: String, var description: String, var bgPicture: String, var headerImage: String) : Serializable {
}