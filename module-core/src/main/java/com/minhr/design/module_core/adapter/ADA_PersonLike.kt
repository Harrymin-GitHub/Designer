package com.minhr.design.module_core.adapter

import android.content.Context
import android.widget.ImageView
import com.minhr.design.common_base.adapter.rv.CommonAdapter
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.common_ui.view.NoScrollGridView
import com.minhr.design.module_core.R
import com.minhr.design.module_core.bean.AllfaverEntity

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
class ADA_PersonLike constructor(context: Context) : CommonAdapter<AllfaverEntity.FaverBean>(context) {
    override fun convert(holder: ViewHolder, bean: AllfaverEntity.FaverBean, position: Int) {
        GlideUtils.loadNormalCircleImage(bean.avatarPath, holder.getView<ImageView>(R.id.iv_avatarPath), 0)
        holder.setText(R.id.tv_nickName, bean.nickName)
        holder.setText(R.id.tv_feedsSize, "喜欢了" + bean.feedsSize + "件商品")
        holder.setText(R.id.tv_time, bean.time)
        val gridView = holder.getView<NoScrollGridView>(R.id.girdview)
        var mAdapter = ADA_PersonChildLike(mContext)
        gridView.adapter = mAdapter
        mAdapter.update(bean.feeds, true)
    }
//    override fun convert(holder: ViewHolderListView, bean: AllfaverEntity.FaverBean, position: Int) {
//        GlideUtils.loadNormalCircleImage(bean.avatarPath, holder.getView<ImageView>(R.id.iv_avatarPath), 0)
//        holder.setText(R.id.tv_nickName, bean.nickName)
//        holder.setText(R.id.tv_feedsSize, "喜欢了" + bean.feedsSize + "件商品")
//        holder.setText(R.id.tv_time, bean.time)
//        val gridView = holder.getView<NoScrollGridView>(R.id.girdview)
//        var mAdapter = ADA_PersonChildLike(mContext)
//        gridView.adapter = mAdapter
//        mAdapter.update(bean.feeds, true)
//    }


    override fun itemLayoutId(): Int = R.layout.item_person_like
}