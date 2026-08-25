package com.minhr.design.module_discover.adapter

import android.content.Context
import android.widget.ImageView
import com.minhr.design.common_base.adapter.rv.CommonAdapter
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_discover.R
import com.minhr.design.module_discover.bean.CategoryBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 分类列表
 */
class ADA_Category constructor(context: Context) : CommonAdapter<CategoryBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_category

    override fun convert(holder: ViewHolder, bean: CategoryBean, position: Int) {
        GlideUtils.loadImage(bean.bgPicture, holder.getView<ImageView>(R.id.iv_bg_img), 0)
        holder.setText(R.id.tv_name, "#" + bean.name)
    }
}