package com.minhr.design.module_core.bean

/**
 * 想去 api.xiangqu.com 已下线时的「逛」页本地兜底数据，仅用于演示 UI。
 */
object ShoppingMockData {

    fun categoryList(): List<CategoryEntity> = listOf(
        CategoryEntity(logo = img(201), name = "女装", id = "1"),
        CategoryEntity(logo = img(202), name = "男装", id = "2"),
        CategoryEntity(logo = img(203), name = "家居", id = "3"),
        CategoryEntity(logo = img(204), name = "美妆", id = "4"),
        CategoryEntity(logo = img(206), name = "配饰", id = "5"),
        CategoryEntity(logo = img(208), name = "数码", id = "6"),
        CategoryEntity(logo = img(219), name = "母婴", id = "7"),
        CategoryEntity(logo = img(221), name = "运动", id = "8")
    )

    fun goodsList(): List<GoodsEntity> = listOf(
        GoodsEntity(
            id = 101,
            h5Url = "https://www.baidu.com",
            image = banner(1015),
            items = listOf(
                product("1001", "软语", 292, "细银手链", "128"),
                product("1002", "青禾", 294, "棉麻衬衫", "268"),
                product("1003", "白川", 296, "陶瓷杯", "89")
            ),
            name = "春日新作",
            title = "轻盈面料 · 日常可穿"
        ),
        GoodsEntity(
            id = 102,
            h5Url = "https://www.baidu.com",
            image = banner(1025),
            items = listOf(
                product("2001", "纸间", 291, "手账本", "58"),
                product("2002", "草木", 225, "香薰蜡烛", "96"),
                product("2003", "北巷", 237, "帆布托特", "159")
            ),
            name = "生活美学",
            title = "把仪式感留在桌上"
        ),
        GoodsEntity(
            id = 103,
            h5Url = "https://www.baidu.com",
            image = banner(1039),
            items = listOf(
                product("3001", "山野", 235, "羊毛围巾", "199"),
                product("3002", "听雨", 223, "极简手表", "459"),
                product("3003", "南风", 219, "亚麻床品", "329")
            ),
            name = "材质精选",
            title = "触感优先的好物"
        )
    )

    fun handPickedGoods(): RecordsEntity = RecordsEntity(
        page = 1,
        size = 10,
        records = listOf(
            record("4001", "软语", 128, "细银手链", "128.00", 292),
            record("4002", "青禾", 86, "棉麻衬衫", "268.00", 294),
            record("4003", "白川", 64, "陶瓷杯套装", "89.00", 296),
            record("4004", "纸间", 52, "手账礼盒", "128.00", 291),
            record("4005", "草木", 41, "香薰礼盒", "168.00", 225),
            record("4006", "北巷", 37, "帆布托特包", "159.00", 237)
        )
    )

    fun personLike(): List<AllfaverEntity> = listOf(
        AllfaverEntity(
            time = "今天",
            list = listOf(
                AllfaverEntity.FaverBean(
                    avatarPath = avatar(64),
                    feeds = listOf(
                        feed("5001", 32, 292),
                        feed("5002", 18, 294),
                        feed("5003", 26, 296)
                    ),
                    nickName = "小林",
                    time = "10分钟前",
                    feedsSize = 3
                ),
                AllfaverEntity.FaverBean(
                    avatarPath = avatar(91),
                    feeds = listOf(
                        feed("5004", 45, 291),
                        feed("5005", 21, 225),
                        feed("5006", 17, 237),
                        feed("5007", 29, 235)
                    ),
                    nickName = "阿柚",
                    time = "1小时前",
                    feedsSize = 4
                )
            ),
            date = "2026-08-25"
        )
    )

    private fun img(id: Int) = "https://picsum.photos/id/$id/200/200"
    private fun banner(id: Int) = "https://picsum.photos/id/$id/960/600"
    private fun avatar(id: Int) = "https://picsum.photos/id/$id/120/120"

    private fun product(id: String, brand: String, photoId: Int, keyword: String, price: String) =
        GoodsEntity.ItemProductBean(
            id = id,
            brandName = brand,
            image = img(photoId),
            keyword = keyword,
            price = price
        )

    private fun record(
        id: String,
        brand: String,
        favNum: Int,
        desc: String,
        price: String,
        photoId: Int
    ) = RecordsEntity.RecordsBean(
        productId = id,
        id = id,
        avaPath = avatar(64),
        brand = brand,
        favNum = favNum,
        description = desc,
        image = img(photoId),
        nickName = "设计师",
        productDescription = desc,
        time = "刚刚",
        price = price
    )

    private fun feed(id: String, favNum: Int, photoId: Int) =
        AllfaverEntity.FaverBean.FeedBean(
            productId = id,
            id = id,
            favNum = favNum,
            image = img(photoId)
        )
}