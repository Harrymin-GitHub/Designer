package com.minhr.design.module_core.adapter

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.minhr.design.common_base.adapter.rv.ItemViewDelegate
import com.minhr.design.common_base.adapter.rv.MultiItemTypeAdapter
import com.minhr.design.common_base.adapter.rv.ViewHolder
import com.minhr.design.common_base.config.arouter.ARouterConfig
import com.minhr.design.common_base.config.arouter.ARouterConstants
import com.minhr.design.common_ui.view.NoScrollGridView
import com.minhr.design.module_core.R
import com.minhr.design.module_core.R.id.rv
import com.minhr.design.module_core.bean.ContentBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品分类Adapter
 */
class GoodsCategoryViewType constructor(context: Context) : ItemViewDelegate<ContentBean> {
    var mContext: Context? = null

    init {
        mContext = context
    }

    override fun getItemViewLayoutId(): Int = R.layout.goods_category_view_type

    override fun isForViewType(item: ContentBean, position: Int): Boolean {
        return item.type == 1
    }

    override fun convert(holder: ViewHolder, bean: ContentBean, position: Int) {
        var gv = holder.getView<NoScrollGridView>(R.id.gv)
        var adapter = ADA_ItemGoodsCategory(mContext!!)
        gv.adapter = adapter
        adapter.update(bean.categorys, true)

        gv.setOnItemClickListener { adapterView, view, i, l ->
            ARouter.getInstance().build(ARouterConfig.ACT_GoodsFilter)
                    .withString(ARouterConstants.SEARCH_KEYWORDS, adapter.dataList[i].name)
                    .withString(ARouterConstants.TAGCATEGORY_ID,  adapter.dataList[i].id)
                    .navigation()
        }
    }
}