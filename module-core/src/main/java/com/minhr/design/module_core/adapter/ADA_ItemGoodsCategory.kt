package com.minhr.design.module_core.adapter

import android.content.Context
import com.minhr.design.common_base.adapter.lv.CommonAdapterListView
import com.minhr.design.common_base.adapter.lv.ViewHolderListView
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_core.R
import com.minhr.design.module_core.bean.CategoryEntity

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
class ADA_ItemGoodsCategory constructor(context: Context) : CommonAdapterListView<CategoryEntity>(context) {
    override fun convert(holder: ViewHolderListView, bean: CategoryEntity, position: Int) {
        GlideUtils.loadNormalImage(bean.logo, holder.getView(R.id.iv_category_logo), 0)
        holder.setText(R.id.tv_category_name, bean.name)
    }
    override fun itemLayoutId(): Int = R.layout.item_goods_category
}