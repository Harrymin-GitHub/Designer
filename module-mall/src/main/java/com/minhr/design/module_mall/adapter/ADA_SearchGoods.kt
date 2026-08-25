package com.minhr.design.module_mall.adapter

import android.content.Context
import android.widget.ImageView
import com.minhr.design.common_base.adapter.rv.CommonAdapter
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_mall.R
import com.minhr.design.module_mall.bean.RecordsEntity

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 搜索商品列表结果Adapter
 */
class ADA_SearchGoods constructor(context: Context) : CommonAdapter<RecordsEntity.RecordsBean>(context) {
    override fun convert(holder: ViewHolder, bean: RecordsEntity.RecordsBean, position: Int) {
        GlideUtils.loadNormalImage(bean.image, holder.getView<ImageView>(R.id.iv_image), 0)
        holder.setText(R.id.tv_price, "￥" + bean.price)
        holder.setText(R.id.tv_brand, bean.brand)
        holder.setText(R.id.tv_productDescription, bean.productDescription)
        holder.setText(R.id.tv_favNum, bean.favNum.toString())
    }

    override fun itemLayoutId(): Int = R.layout.item_search_goods
}