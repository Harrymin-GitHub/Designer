package com.minhr.design.module_discover.adapter

import android.content.Context
import com.minhr.design.common_base.adapter.rv.ItemViewDelegate
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.module_discover.R
import com.minhr.design.module_discover.bean.ItemEntity

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : type=textHeader或者type=textFooter
 */
class TextViewType constructor(context: Context) : ItemViewDelegate<ItemEntity.ItemDataEntity> {
    override fun getItemViewLayoutId(): Int = R.layout.item_text_title
    override fun isForViewType(item: ItemEntity.ItemDataEntity, position: Int): Boolean = (item.type == "textHeader" || item.type == "textFooter")

    override fun convert(holder: ViewHolder, bean: ItemEntity.ItemDataEntity, position: Int) {
        holder.setText(R.id.tv_title, bean?.data?.text)
    }

}
