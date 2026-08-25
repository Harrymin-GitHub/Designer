package com.minhr.design.module_core.adapter

import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.minhr.design.common_base.adapter.lv.CommonAdapterListView
import com.minhr.design.common_base.adapter.lv.ViewHolderListView
import com.minhr.design.common_base.config.arouter.ARouterConfig
import com.minhr.design.common_base.config.arouter.ARouterConstants
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_core.R
import com.minhr.design.module_core.bean.TagCategoryEntity

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 设计分类标签Adapter
 */
class ADA_DesignerTypeList constructor(context: Context) : CommonAdapterListView<TagCategoryEntity.TagBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_designer_tag

    override fun convert(holder: ViewHolderListView, bean: TagCategoryEntity.TagBean, position: Int) {
        GlideUtils.loadNormalImage(bean.image, holder.getView(R.id.iv_cover), 0)
        holder.setText(R.id.tv_type, bean.name)

        holder.convertView.setOnClickListener {
            ARouter.getInstance().build(ARouterConfig.ACT_DESIGNERLIST)
                    .withString(ARouterConstants.TAGCATEGORY_ID, bean.categoryId)
                    .withString(ARouterConstants.TAG_ID, bean.id)
                    .withString(ARouterConstants.TAG_NAME, bean.name)
                    .navigation()
        }
    }

}