package com.minhr.design.common_base.config.arouter

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : base-路由页面常量配置 注意：路径至少需要两级 {/xx/xx}
 */
class ARouterConfig {
    companion object {
        //const声明编译时常量
        //想去app
        const val ACT_WEBVIEW = "/base/act_commonweb"
        const val ACT_DESIGNERLIST = "/designer/act_designerlist"
        const val ACT_SEARCH = "/mall/act_search"
        const val ACT_GoodsFilter= "/mall/act_goodsfilter"
        const val ACT_GoodsDetail= "/mall/act_goodsdetail"
        //开眼视频app
        const val ACT_Category= "/found/act_category"
        const val ACT_CategoryDetail= "/found/act_categorydetail"
        const val ACT_PlayVideo= "/found/act_playvideo"
    }

}