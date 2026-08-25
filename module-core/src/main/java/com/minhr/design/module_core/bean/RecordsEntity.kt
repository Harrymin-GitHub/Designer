package com.minhr.design.module_core.bean

import java.io.Serializable

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : search/list实体类
 */
data class RecordsEntity(var page: Int, var size: Int, var records: List<RecordsBean>) : Serializable {
    data class RecordsBean(var productId: String, var id: String, var avaPath: String, var brand: String, var favNum: Int, var description: String,
                           var image: String, var nickName: String, var productDescription: String,
                           var time: String, var price: String) : Serializable {
    }
}
