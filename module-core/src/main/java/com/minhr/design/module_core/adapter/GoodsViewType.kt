package com.minhr.design.module_core.adapter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minhr.design.common_base.BaseAppliction.Companion.context
import com.minhr.design.common_base.adapter.rv.ItemViewDelegate
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.module_core.R
import com.minhr.design.module_core.bean.ContentBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品列表（大图+商品列表）
 */
class GoodsViewType constructor(context: Context) : ItemViewDelegate<ContentBean> {
    var mContext: Context? = null

    init {
        mContext = context
    }

    override fun getItemViewLayoutId(): Int = R.layout.goods_view_type

    override fun isForViewType(item: ContentBean, position: Int): Boolean {
        return item.type == 2
    }

    override fun convert(holder: ViewHolder, bean: ContentBean, position: Int) {
        var adapter = ADA_ItemGoods(mContext!!)
        var rv = holder.getView<RecyclerView>(R.id.rv)
        rv.layoutManager =
            LinearLayoutManager(mContext)
        rv.adapter = adapter
        adapter.update(bean.goods, true)

    }
}