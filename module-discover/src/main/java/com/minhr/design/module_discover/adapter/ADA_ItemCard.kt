package com.minhr.design.module_discover.adapter

import android.content.Context
import android.widget.ImageView
import com.alibaba.android.arouter.launcher.ARouter
import com.minhr.design.common_base.adapter.rv.CommonAdapter
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.common_base.config.arouter.ARouterConfig
import com.minhr.design.common_base.config.arouter.ARouterConstants
import com.minhr.design.common_base.utils.DataFormatUtils
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_discover.R
import com.minhr.design.module_discover.bean.ItemEntity

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 分类详情-横向子列表
 */
class ADA_ItemCard constructor(context: Context) : CommonAdapter<ItemEntity.ItemDataEntity.DataEntity.ItemBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_card

    override fun convert(holder: ViewHolder, bean: ItemEntity.ItemDataEntity.DataEntity.ItemBean, position: Int) {
        var ivCard = holder.getView<ImageView>(R.id.iv_card)
        bean?.data?.cover?.detail?.let { GlideUtils.loadNormalImage(it, ivCard, 0) }
        if (bean?.data != null && bean.data.playInfo != null && bean.data.playInfo.isNotEmpty()) {
            holder.setText(R.id.tv_size, DataFormatUtils.formatTime((bean.data.playInfo[0].urlList[0].size ?: 0L) / 100))
        }

        holder.convertView.setOnClickListener {
            ARouter.getInstance().build(ARouterConfig.ACT_PlayVideo)
//                    .withSerializable(ARouterConstants.PLAY_VIDEO_BEAN, bean)
                    .withString(ARouterConstants.PLAY_URL, bean.data.playUrl)
                    .withString(ARouterConstants.PLAY_TITLE, bean.data.title)
                    .withString(ARouterConstants.COVER_IMG, bean.data.cover.detail)
                    .withString(ARouterConstants.VIDEO_BG_IMG, bean.data.cover.blurred)
                    .navigation()
        }
    }
}