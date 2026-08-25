package com.minhr.design.module_core.adapter

import android.content.Context
import android.widget.ImageView
import com.minhr.design.common_base.adapter.rv.CommonAdapter
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_core.R
import com.minhr.design.module_core.bean.DesignerEntity

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 设计师列表
 */
class ADA_DesignerList constructor(context: Context) : CommonAdapter<DesignerEntity>(context) {
    override fun convert(holder: ViewHolder, bean: DesignerEntity, position: Int) {
        bean.userAvatar?.let {
            GlideUtils.loadNormalCircleImage(it, holder.getView<ImageView>(R.id.iv_userAvatar), 0)
        }
        holder.setText(R.id.tv_userNick, bean.userNick)
        holder.setText(R.id.tv_opTag, bean.opTag)
        holder.setText(R.id.tv_productNum, bean.productNum.toString() + "个作品")
        when (bean.tags.size) {
            1 -> holder.setText(R.id.tv_tags, bean.tags[0].name)
            2 -> holder.setText(R.id.tv_tags, bean.tags[0].name + "  " + bean.tags[1].name)
            3 -> holder.setText(R.id.tv_tags, bean.tags[0].name + "  " + bean.tags[1].name + "  " + bean.tags[2].name)
        }
    }

    override fun itemLayoutId(): Int = R.layout.item_designer_list
}