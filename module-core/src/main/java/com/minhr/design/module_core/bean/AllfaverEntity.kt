package com.minhr.design.module_core.bean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : tab-大家喜欢
 */
data class AllfaverEntity(var time: String, var list: List<FaverBean>, var date: String) {
    data class FaverBean(var avatarPath: String, var feeds: List<FeedBean>, var nickName: String, var time: String, var feedsSize: Int) {
        data class FeedBean(var productId: String, var id: String, var favNum: Int, var image: String) {
        }
    }
}