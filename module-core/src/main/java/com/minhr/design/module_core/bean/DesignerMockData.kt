package com.minhr.design.module_core.bean

/**
 * 想去 api.xiangqu.com 已下线时的本地设计师兜底数据，仅用于演示 UI。
 */
object DesignerMockData {

    fun recommendDesigner(): DesignerEntity = DesignerEntity(
        id = "196",
        userAvatar = "https://picsum.photos/id/64/120/120",
        productNum = 41,
        tags = listOf(
            DesignerEntity.TagBean("配饰"),
            DesignerEntity.TagBean("中国风")
        ),
        userNick = "草木",
        banner = "https://picsum.photos/id/1015/720/480",
        opTag = "品牌创始人、设计师",
        shopName = "软语 金银细软珠宝店",
        shopId = "70wv8vnw"
    )

    fun typeList(): List<TagCategoryEntity> = listOf(
        TagCategoryEntity(
            id = "1",
            name = "行业",
            tags = listOf(
                tag("1", "101", "配饰", 201),
                tag("1", "102", "服装", 202),
                tag("1", "103", "家居", 203),
                tag("1", "104", "美妆", 204),
                tag("1", "105", "数码", 206),
                tag("1", "106", "文具", 208)
            )
        ),
        TagCategoryEntity(
            id = "2",
            name = "风格",
            tags = listOf(
                tag("2", "201", "中国风", 225),
                tag("2", "202", "北欧风", 219),
                tag("2", "203", "日系", 221),
                tag("2", "204", "极简", 223),
                tag("2", "205", "复古", 235),
                tag("2", "206", "街头", 237)
            )
        ),
        TagCategoryEntity(
            id = "3",
            name = "材质",
            tags = listOf(
                tag("3", "301", "棉麻", 292),
                tag("3", "302", "真丝", 294),
                tag("3", "303", "皮革", 296),
                tag("3", "304", "金属", 291)
            )
        )
    )

    fun designerList(): List<DesignerEntity> = listOf(
        DesignerEntity(
            id = "101",
            userAvatar = "https://picsum.photos/id/64/120/120",
            productNum = 41,
            tags = listOf(DesignerEntity.TagBean("配饰"), DesignerEntity.TagBean("中国风")),
            userNick = "草木",
            banner = "https://picsum.photos/id/1015/720/480",
            opTag = "品牌创始人、设计师",
            shopName = "软语珠宝店",
            shopId = "shop1"
        ),
        DesignerEntity(
            id = "102",
            userAvatar = "https://picsum.photos/id/91/120/120",
            productNum = 28,
            tags = listOf(DesignerEntity.TagBean("服装"), DesignerEntity.TagBean("极简")),
            userNick = "青禾",
            banner = "https://picsum.photos/id/1025/720/480",
            opTag = "独立设计师",
            shopName = "青禾工作室",
            shopId = "shop2"
        ),
        DesignerEntity(
            id = "103",
            userAvatar = "https://picsum.photos/id/177/120/120",
            productNum = 56,
            tags = listOf(DesignerEntity.TagBean("家居"), DesignerEntity.TagBean("北欧风")),
            userNick = "白川",
            banner = "https://picsum.photos/id/1039/720/480",
            opTag = "生活美学设计师",
            shopName = "白川家居",
            shopId = "shop3"
        ),
        DesignerEntity(
            id = "104",
            userAvatar = "https://picsum.photos/id/338/120/120",
            productNum = 19,
            tags = listOf(DesignerEntity.TagBean("文具"), DesignerEntity.TagBean("日系")),
            userNick = "纸间",
            banner = "https://picsum.photos/id/1060/720/480",
            opTag = "文创设计师",
            shopName = "纸间造物",
            shopId = "shop4"
        )
    )

    private fun tag(categoryId: String, id: String, name: String, photoId: Int) =
        TagCategoryEntity.TagBean(
            categoryId = categoryId,
            id = id,
            image = "https://picsum.photos/id/$photoId/240/240",
            name = name
        )
}