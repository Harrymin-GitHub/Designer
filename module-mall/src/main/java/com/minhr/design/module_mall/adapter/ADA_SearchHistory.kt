package com.minhr.design.module_mall.adapter

import android.content.Context
import com.minhr.design.common_base.adapter.rv.CommonAdapter
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.module_mall.R
import com.minhr.design.module_mall.db.bean.SearchHistoryBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 搜索历史
 */
class ADA_SearchHistory constructor(context: Context) : CommonAdapter<SearchHistoryBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_search_history

    override fun convert(holder: ViewHolder, bean: SearchHistoryBean, position: Int) {
        holder.setText(R.id.tv_keyword, bean.searchKeyWords)
    }
}