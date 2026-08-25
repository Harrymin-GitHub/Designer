package com.minhr.design.module_mall.bean

/**
 * 想去 api.xiangqu.com 已下线时的商品详情本地兜底数据，仅用于演示 UI。
 */
object GoodsMockData {

    private fun img(id: Int) = "https://picsum.photos/id/$id/480/480"
    private fun avatar(id: Int) = "https://picsum.photos/id/$id/96/96"

    fun goodsContent(productId: String = "9001"): GoodsContentBean {
        val cover = img(292)
        return GoodsContentBean(
            productId = productId.ifBlank { "9001" },
            image = cover,
            imgsUrlList = listOf(img(294), img(296), img(291)),
            title = "设计师精选好物 · 棉麻衬衫",
            favNum = "286",
            brandStory = "品牌故事：以自然材质与简约剪裁为灵感，坚持小批量手作。每一件作品都希望带来日常里的松弛感与温度，让穿着与使用本身成为生活的一部分。工作室从选材、打版到成衣都会反复打磨细节，希望用克制的设计语言，表达对生活美学的理解，也让更多人在平凡日子里感受到恰到好处的体面与自在。",
            price = "268.00",
            productDiscountTxt = "登录后分享该商品可获得优惠券（演示）",
            postage = "",
            guarantees = listOf(
                GoodsContentBean.GuaranteeBean("7天无理由退货"),
                GoodsContentBean.GuaranteeBean("48小时发货"),
                GoodsContentBean.GuaranteeBean("担保交易")
            ),
            avaPath = avatar(64),
            productUser = "青禾",
            brandIcon = img(237),
            brand = "软语工作室",
            platFormWeixin = GoodsContentBean.PlatFormWeixinBean(
                desc = "加想去君微信（演示）：了解更多设计师与限时福利～"
            )
        )
    }

    fun goodsDescription(): List<GoodsDesBean> = listOf(
        GoodsDesBean(
            content = "商品介绍\n材质：棉麻混纺\n尺码：S / M / L / XL\n版型：宽松\n适用季节：春夏\n设计亮点：简约剪裁、透气舒适，适合日常通勤与周末出行。",
            isImg = false
        ),
        GoodsDesBean(content = img(225), isImg = true),
        GoodsDesBean(content = img(219), isImg = true),
        GoodsDesBean(
            content = "洗涤说明：建议手洗或轻柔机洗，阴凉处晾干，避免暴晒。",
            isImg = false
        )
    )

    fun commentList(): List<CommentBean> = listOf(
        CommentBean(avaPath = avatar(65), nick = "阿茉", content = "面料很舒服，版型也好看，值得推荐！", time = "2024年03月12日"),
        CommentBean(avaPath = avatar(91), nick = "林间", content = "做工细致，颜色和图片一致。", time = "2024年02月28日"),
        CommentBean(avaPath = avatar(102), nick = "白川", content = "包装用心，第二次回购了。", time = "2024年01月15日")
    )

    fun revelentGoods(): RevelentBean = RevelentBean(
        revelentList = listOf(
            RevelentBean.RevelentItemBean(imageUrl = img(201), id = "9002"),
            RevelentBean.RevelentItemBean(imageUrl = img(202), id = "9003"),
            RevelentBean.RevelentItemBean(imageUrl = img(203), id = "9004"),
            RevelentBean.RevelentItemBean(imageUrl = img(206), id = "9005"),
            RevelentBean.RevelentItemBean(imageUrl = img(208), id = "9006"),
            RevelentBean.RevelentItemBean(imageUrl = img(221), id = "9007")
        )
    )
}
