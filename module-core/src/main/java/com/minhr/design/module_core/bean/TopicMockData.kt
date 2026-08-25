package com.minhr.design.module_core.bean

/**
 * 想去 api.xiangqu.com 已下线时的本地专题兜底数据，仅用于演示卡片 UI。
 */
object TopicMockData {

    fun topics(): List<TopicBean> = listOf(
        TopicBean(
            id = "1",
            name = "春日轻盈穿搭",
            image = "https://picsum.photos/id/1015/400/600",
            type = 1,
            url = "app://local/preview"
        ),
        TopicBean(
            id = "2",
            name = "设计师灵感集",
            image = "https://picsum.photos/id/1025/400/600",
            type = 1,
            url = "app://local/preview"
        ),
        TopicBean(
            id = "3",
            name = "周末慢生活",
            image = "https://picsum.photos/id/1039/400/600",
            type = 1,
            url = "app://local/preview"
        ),
        TopicBean(
            id = "4",
            name = "材质与工艺",
            image = "https://picsum.photos/id/1060/400/600",
            type = 1,
            url = "app://local/preview"
        )
    )

    fun topicDetail(id: String = "5192"): TopicDetailEntity = TopicDetailEntity(
        list = listOf(
            TopicDetailEntity.BannerBean(image = "https://picsum.photos/id/1015/720/405", text = "春日灵感"),
            TopicDetailEntity.BannerBean(image = "https://picsum.photos/id/1025/720/405", text = "材质之美"),
            TopicDetailEntity.BannerBean(image = "https://picsum.photos/id/1039/720/405", text = "慢生活")
        ),
        name = "精选专题",
        tagid = id
    )
}