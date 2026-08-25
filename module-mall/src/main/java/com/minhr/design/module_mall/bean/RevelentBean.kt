package com.minhr.design.module_mall.bean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品详情-底部相似推荐列表实体
 */
data class RevelentBean(var revelentList: List<RevelentItemBean>) {
    data class RevelentItemBean(var imageUrl: String, var id: String)
//    {
//        "height": 210,
//        "id": 3115726,
//        "imageUrl": "http://xqproduct.xiangqu.com/Fnr4jGiNDq7Q1Fft1h_KOfbTPhWF?imageView2/2/w/210/q/90/format/jpg/800x800/",
//        "width": 210
//    }
}