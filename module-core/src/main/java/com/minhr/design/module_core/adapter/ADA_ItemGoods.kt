package com.minhr.design.module_core.adapter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import com.alibaba.android.arouter.launcher.ARouter
import com.minhr.design.common_base.adapter.rv.CommonAdapter
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.common_base.config.arouter.ARouterConfig
import com.minhr.design.common_base.config.arouter.ARouterConstants
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_core.R
import com.minhr.design.module_core.bean.GoodsEntity
import com.minhr.design.module_core.ui.common.ACT_CommonWebView

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 逛-（图片+子列表）适配器
 */
class ADA_ItemGoods constructor(context: Context) : CommonAdapter<GoodsEntity>(context) {
    override fun convert(holder: ViewHolder, bean: GoodsEntity, position: Int) {
        GlideUtils.loadNormalImage(bean.image, holder.getView<ImageView>(R.id.iv_image), 0)
        holder.setText(R.id.tv_name, bean.name)
        holder.setText(R.id.tv_title, bean.title)
        val rvGoods = holder.getView<RecyclerView>(R.id.rl_goods)
        var adapter = ADA_ChildGoods(mContext!!)
        var rv = holder.getView<RecyclerView>(R.id.rv)
        rvGoods.layoutManager = LinearLayoutManager(
            mContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rvGoods.adapter = adapter
        adapter.update(bean.items, true)

        holder.setOnClickListener(R.id.iv_image, {
            ARouter.getInstance().build(ARouterConfig.ACT_WEBVIEW)
                .withString(ARouterConstants.WEB_TITLE, bean.name)
                .withString(ARouterConstants.WEB_URL, ACT_CommonWebView.LOCAL_PREVIEW_SCHEME)
                .withString(ARouterConstants.WEB_IMAGE, bean.image)
                .withString(
                    ARouterConstants.WEB_DESC,
                    bean.title.ifBlank { "商品专题演示（原想去 H5 已下线）" }
                )
                .navigation()
        })
    }

    override fun itemLayoutId(): Int = R.layout.item_goods
}