package com.minhr.design.module_core.adapter

import android.content.Context
import android.widget.ImageView
import com.alibaba.android.arouter.launcher.ARouter
import com.minhr.design.common_base.adapter.lv.CommonAdapterListView
import com.minhr.design.common_base.adapter.lv.ViewHolderListView
import com.minhr.design.common_base.config.arouter.ARouterConfig
import com.minhr.design.common_base.config.arouter.ARouterConstants
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_core.R
import com.minhr.design.module_core.bean.AllfaverEntity

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 大家喜欢，子列表适配器
 */
class ADA_PersonChildLike constructor(context: Context) : CommonAdapterListView<AllfaverEntity.FaverBean.FeedBean>(context) {
    override fun convert(holder: ViewHolderListView, bean: AllfaverEntity.FaverBean.FeedBean, position: Int) {
        GlideUtils.loadNormalImage(bean.image, holder.getView<ImageView>(R.id.iv_image), 0)
        holder.setText(R.id.tv_favNum, bean.favNum.toString())

        holder.setOnClickListener(R.id.iv_image, {
            ARouter.getInstance().build(ARouterConfig.ACT_GoodsDetail)
                    .withString(ARouterConstants.PRODUCT_ID, bean.productId)
                    .navigation()
        })
    }


    override fun itemLayoutId(): Int = R.layout.item_person_child_like
}