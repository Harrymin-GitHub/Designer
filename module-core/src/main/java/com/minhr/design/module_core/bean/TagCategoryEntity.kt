package com.minhr.design.module_core.bean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 设计师-标签类别实体
 */
data class TagCategoryEntity(private val id: String, val name: String, val tags: List<TagBean>) {
    data class TagBean(var categoryId: String, var id: String, val image: String, val name: String) {

    }
}