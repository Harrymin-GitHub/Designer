package com.minhr.design.module_mall.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import com.minhr.design.common_base.adapter.lv.CommonAdapterListView
import com.minhr.design.common_base.adapter.lv.ViewHolderListView
import com.minhr.design.module_mall.R
import com.minhr.design.module_mall.bean.FilterBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 筛选框中的价格区间列表适配器
 */
class ADA_FilterService constructor(context: Context) : CommonAdapterListView<FilterBean.PromotionTagBean>(context) {
    override fun convert(holder: ViewHolderListView, bean: FilterBean.PromotionTagBean, position: Int) {
        holder.setText(R.id.tv_value, bean.value)
        if (bean.isCheck) {
            holder.setTextColor(R.id.tv_value,ContextCompat.getColor(mContext,R.color.color_ffffff))
            holder.setBackgroundRes(R.id.tv_value,R.drawable.bg_shape_app_yellow_6)
        }else{
            holder.setTextColor(R.id.tv_value,ContextCompat.getColor(mContext,R.color.color_858585))
            holder.setBackgroundRes(R.id.tv_value,R.drawable.bg_shape_ffffff_6)
        }
    }

    override fun itemLayoutId(): Int = R.layout.item_filter_service

}