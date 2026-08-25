package com.minhr.design.module_core.adapter

import android.content.Context
import android.widget.ImageView
import com.alibaba.android.arouter.launcher.ARouter
import com.minhr.design.common_base.adapter.rv.CommonAdapter
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.common_base.config.arouter.ARouterConfig
import com.minhr.design.common_base.config.arouter.ARouterConstants
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_core.R
import com.minhr.design.module_core.bean.GoodsEntity

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 逛-横线滑动的商品列表Adapter
 */
class ADA_ChildGoods constructor(context: Context) : CommonAdapter<GoodsEntity.ItemProductBean>(context) {
    override fun convert(holder: ViewHolder, bean: GoodsEntity.ItemProductBean, position: Int) {
        GlideUtils.loadNormalImage(bean.image, holder.getView<ImageView>(R.id.iv_image), 0)
        holder.setText(R.id.tv_branch_name, bean.brandName)
        holder.setText(R.id.tv_keyword, bean.keyword)
        holder.setText(R.id.tv_price, "￥" + bean.price)

        holder.setOnClickListener(R.id.iv_image, {
            ARouter.getInstance().build(ARouterConfig.ACT_GoodsDetail)
                    .withString(ARouterConstants.PRODUCT_ID, bean.id)
                    .navigation()
        })
    }

    override fun itemLayoutId(): Int = R.layout.item_child_goods
}