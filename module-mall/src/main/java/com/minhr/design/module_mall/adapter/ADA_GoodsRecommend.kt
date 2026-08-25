package com.minhr.design.module_mall.adapter

import android.content.Context
import com.minhr.design.common_base.adapter.lv.CommonAdapterListView
import com.minhr.design.common_base.adapter.lv.ViewHolderListView
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_mall.R
import com.minhr.design.module_mall.bean.RevelentBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品内容（图片+文字）
 */
class ADA_GoodsRecommend constructor(context: Context) : CommonAdapterListView<RevelentBean.RevelentItemBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_goods_recommend

    override fun convert(holder: ViewHolderListView, bean: RevelentBean.RevelentItemBean, position: Int) {
        GlideUtils.loadNormalImage(bean.imageUrl, holder.getView(R.id.iv_image), 0)
    }
}