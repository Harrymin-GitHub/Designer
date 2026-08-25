package com.minhr.design.module_mall.bean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
data class FilterBean(var promotionTags: List<PromotionTagBean>, var stageRange: List<String>) {
    data class PromotionTagBean(var key: String, var value: String, var isCheck: Boolean)
}