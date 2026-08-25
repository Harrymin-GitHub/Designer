package com.minhr.design.module_mall.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import com.minhr.design.common_base.adapter.rv.CommonAdapter
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.module_mall.R
import com.minhr.design.module_mall.bean.CategoryBean
import com.minhr.design.module_mall.db.bean.SearchHistoryBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 选择分类列表Adapter
 */
class ADA_ChooseCategory constructor(context: Context) : CommonAdapter<CategoryBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_choose_category

    override fun convert(holder: ViewHolder, bean: CategoryBean, position: Int) {
        holder.setText(R.id.tv_name, bean.name)
        if (bean.isCheck) {
            holder.setTextColor(R.id.tv_name, ContextCompat.getColor(mContext, R.color.color_app_yellow))
            holder.setVisible(R.id.iv_check, true)
        } else {
            holder.setTextColor(R.id.tv_name, ContextCompat.getColor(mContext, R.color.color_858585))
            holder.setVisible(R.id.iv_check, false)
        }
    }
}