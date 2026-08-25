package com.minhr.design.module_mall.bean

/**
 * 想去 api.xiangqu.com 已下线时的搜索页本地兜底数据，仅用于演示 UI。
 */
object SearchMockData {

    fun hotList(): List<String> = listOf(
        "棉麻", "极简", "手作银饰", "香薰", "北欧家居",
        "帆布包", "真丝", "陶瓷", "手账", "羊毛围巾"
    )

    fun searchGoods(keyword: String = ""): RecordsEntity {
        val tip = if (keyword.isBlank()) "精选好物" else keyword
        return RecordsEntity(
            page = 0,
            size = 10,
            records = listOf(
                record("9001", "软语", 128, tip, "细银手链", "128.00", 292),
                record("9002", "青禾", 86, tip, "棉麻衬衫", "268.00", 294),
                record("9003", "白川", 64, tip, "陶瓷杯套装", "89.00", 296),
                record("9004", "纸间", 52, tip, "手账礼盒", "128.00", 291),
                record("9005", "草木", 41, tip, "香薰礼盒", "168.00", 225),
                record("9006", "北巷", 37, tip, "帆布托特包", "159.00", 237)
            )
        )
    }

    fun categoryList(): List<CategoryBean> = listOf(
        CategoryBean(id = 1, name = "全部", isCheck = true),
        CategoryBean(id = 2, name = "女装", isCheck = false),
        CategoryBean(id = 3, name = "男装", isCheck = false),
        CategoryBean(id = 4, name = "家居", isCheck = false),
        CategoryBean(id = 5, name = "配饰", isCheck = false),
        CategoryBean(id = 6, name = "美妆", isCheck = false)
    )

    fun filterData(): FilterBean = FilterBean(
        promotionTags = listOf(
            FilterBean.PromotionTagBean(key = "new", value = "新品", isCheck = false),
            FilterBean.PromotionTagBean(key = "sale", value = "特惠", isCheck = false),
            FilterBean.PromotionTagBean(key = "hot", value = "热卖", isCheck = false)
        ),
        stageRange = listOf("0-100", "100-300", "300-500", "500-99999")
    )

    private fun img(id: Int) = "https://picsum.photos/id/$id/320/320"
    private fun avatar(id: Int) = "https://picsum.photos/id/$id/96/96"

    private fun record(
        id: String,
        brand: String,
        favNum: Int,
        desc: String,
        productDesc: String,
        price: String,
        photoId: Int
    ) = RecordsEntity.RecordsBean(
        productId = id,
        avaPath = avatar(64),
        brand = brand,
        favNum = favNum,
        description = desc,
        image = img(photoId),
        nickName = "设计师",
        productDescription = productDesc,
        time = "刚刚",
        price = price
    )
}