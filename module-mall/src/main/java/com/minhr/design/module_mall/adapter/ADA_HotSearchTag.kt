package com.smart.novel.adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.minhr.design.common_base.BaseAppliction.Companion.context
import com.minhr.design.common_ui.view.tag.FlowLayout
import com.minhr.design.common_ui.view.tag.TagAdapter
import com.minhr.design.module_mall.R

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 热门搜索标签
 */
class ADA_HotSearchTag constructor(hotList: List<String>) : TagAdapter<String>(hotList) {
    override fun getView(parent: FlowLayout?, position: Int, name: String?): View {
        var itemTagView = LayoutInflater.from(context).inflate(R.layout.item_hot_search, null);
        itemTagView.findViewById<TextView>(R.id.tv_item_name).text = name
        return itemTagView
    }
}