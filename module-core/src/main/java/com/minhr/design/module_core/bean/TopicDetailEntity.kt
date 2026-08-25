package com.minhr.design.module_core.bean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 专题详情（一级）
 */
data class TopicDetailEntity(var list: List<BannerBean>, var name: String, var tagid: String) {
    data class BannerBean(var image: String, var text: String)
}