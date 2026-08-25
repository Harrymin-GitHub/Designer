package com.minhr.design.module_core.bean

import java.io.Serializable

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 逛（列表）包装的bean
 */
data class ContentBean(val type: Int, val categorys: List<CategoryEntity>, val goods: List<GoodsEntity>, var allFavarList: List<AllfaverEntity>? = null, var records: List<RecordsEntity.RecordsBean>? = null) : Serializable {
}