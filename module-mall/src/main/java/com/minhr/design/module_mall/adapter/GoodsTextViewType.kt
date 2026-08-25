package com.minhr.design.module_mall.adapter

import android.content.Context
import com.minhr.design.common_base.BaseAppliction.Companion.context
import com.minhr.design.common_base.adapter.rv.ItemViewDelegate
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.module_mall.R
import com.minhr.design.module_mall.bean.GoodsDesBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品详情
 */
class GoodsTextViewType constructor() : ItemViewDelegate<GoodsDesBean> {
    override fun convert(holder: ViewHolder, bean: GoodsDesBean, position: Int) {
        holder.setText(R.id.tv_content, bean.content)
    }

    override fun isForViewType(item: GoodsDesBean, position: Int): Boolean = !item.isImg

    override fun getItemViewLayoutId(): Int = R.layout.item_goods_des_text
}