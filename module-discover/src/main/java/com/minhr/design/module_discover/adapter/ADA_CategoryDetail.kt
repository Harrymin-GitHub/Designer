package com.minhr.design.module_discover.adapter

import android.app.Activity
import com.minhr.design.common_base.adapter.rv.MultiItemTypeAdapter
import com.minhr.design.module_discover.bean.ItemEntity

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 分类详情-分类型列表
 */
class ADA_CategoryDetail constructor(context: Activity) : MultiItemTypeAdapter<ItemEntity.ItemDataEntity>(context) {
    init {
        super.mContext = context
        addItemViewDelegate(ScrollCardType(context))
        addItemViewDelegate(TextViewType(context))
        addItemViewDelegate(VideoViewType(context))
    }

}