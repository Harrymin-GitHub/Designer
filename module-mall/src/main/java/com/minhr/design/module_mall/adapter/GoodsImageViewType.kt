package com.minhr.design.module_mall.adapter

import com.minhr.design.common_base.adapter.rv.ItemViewDelegate
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_mall.R
import com.minhr.design.module_mall.bean.GoodsDesBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品详情
 */
class GoodsImageViewType constructor() : ItemViewDelegate<GoodsDesBean> {
    override fun convert(holder: ViewHolder, bean: GoodsDesBean, position: Int) {


        //图片展示大小会有问题，设置ScaleType也不好使
//        GlideUtils.loadImage(bean.content, holder.getView(R.id.iv_content), 0)

        GlideUtils.loadOriginalSizeImage(bean.content, holder.getView(R.id.iv_content), 0)
    }

    override fun isForViewType(item: GoodsDesBean, position: Int): Boolean = item.isImg


    override fun getItemViewLayoutId(): Int = R.layout.item_goods_des_image
}