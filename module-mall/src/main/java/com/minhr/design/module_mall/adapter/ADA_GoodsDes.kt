package com.minhr.design.module_mall.adapter

import android.content.Context
import com.minhr.design.common_base.adapter.rv.MultiItemTypeAdapter
import com.minhr.design.module_mall.bean.GoodsDesBean

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品内容（图片+文字）
 */
class ADA_GoodsDes constructor(context: Context) : MultiItemTypeAdapter<GoodsDesBean>(context) {
    init {
        super.mContext = context
        addItemViewDelegate(GoodsImageViewType())
        addItemViewDelegate(GoodsTextViewType())
    }
}