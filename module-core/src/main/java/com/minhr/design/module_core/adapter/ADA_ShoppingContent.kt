package com.minhr.design.module_core.adapter

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.minhr.design.common_base.adapter.rv.MultiItemTypeAdapter
import com.minhr.design.module_core.bean.ContentBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 逛 -分类型列表
 */
class ADA_ShoppingContent constructor(context: Activity) : MultiItemTypeAdapter<ContentBean>(context) {
    init {
        super.mContext = context
        addItemViewDelegate(GoodsCategoryViewType(context))
        addItemViewDelegate(GoodsViewType(context))
//        addItemViewDelegate(ViewPagerViewType(context))
    }

}