package com.minhr.design.module_mall.adapter

import android.content.Context
import com.minhr.design.common_base.adapter.lv.CommonAdapterListView
import com.minhr.design.common_base.adapter.lv.ViewHolderListView
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_mall.R
import com.minhr.design.module_mall.bean.CommentBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品内容（图片+文字）
 */
class ADA_GoodsComment constructor(context: Context) : CommonAdapterListView<CommentBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_comment

    override fun convert(holder: ViewHolderListView, bean: CommentBean, position: Int) {
        holder.setText(R.id.tv_nick, bean.nick)
        holder.setText(R.id.tv_content, bean.content)
        holder.setText(R.id.tv_time, bean.time)
        GlideUtils.loadNormalCircleImage(bean.avaPath, holder.getView(R.id.iv_avaPath), 0)
    }
}