package com.minhr.design.module_core.adapter

import android.content.Context
import com.example.jojo.databindingadapter.recyclerView.CommonAdapter
import com.example.jojo.databindingadapter.recyclerView.ViewHolder
import com.minhr.design.module_core.R
import com.minhr.design.module_core.databinding.ItemTestBinding

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   :
 */
class ADA_TestFragment constructor(context: Context) : CommonAdapter<String, ItemTestBinding>(context) {
    override fun convert(viewBinding: ItemTestBinding?, holder: ViewHolder.BindingHolder?, p2: String?, position: Int) {
        holder?.setText(R.id.tv,p2)
    }

    override fun itemLayoutId(): Int = R.layout.item_test
}