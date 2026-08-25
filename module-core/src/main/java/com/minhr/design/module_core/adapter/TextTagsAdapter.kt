package com.minhr.design.module_core.adapter

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.alibaba.android.arouter.launcher.ARouter
import com.minhr.design.common_base.BaseAppliction
import com.minhr.design.common_base.config.arouter.ARouterConfig
import com.minhr.design.common_base.utils.ToastUtils
import com.minhr.design.module_core.R
import com.minhr.design.module_core.widgets.tagcloud.TagsAdapter

import java.util.ArrayList
import java.util.Collections
import java.util.Random

/**
 * author : Harrymin
 * e-mail : minhongrui@126.com
 * date   : 2026/8/25 7:38 PM
 * desc   : 3D标签云效果适配器
 */
class TextTagsAdapter(data: List<String>) : TagsAdapter() {
    private var mData = ArrayList<String>()

    init {

        mData = data as ArrayList<String>
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getView(context: Context, position: Int, parent: ViewGroup): View {
        val rand = Random()
        val randNum = rand.nextInt(mData.size - 1)

        val tv = TextView(context)
        val lp = ViewGroup.MarginLayoutParams(200, 100)
        tv.layoutParams = lp
        tv.text = mData[randNum]
        tv.gravity = Gravity.CENTER
        tv.setOnClickListener {
            ToastUtils.makeShortToast(mData[randNum])
            if (mData[randNum].contains(BaseAppliction.context.getString(R.string.string_kaiyan))) {
                ARouter.getInstance().build(ARouterConfig.ACT_Category)
                        .navigation()
            }
        }
        return tv
    }

    override fun getItem(position: Int): Any {
        return mData[position]
    }

    override fun getPopularity(position: Int): Int {
        return position % 7
    }

    override fun onThemeColorChanged(view: View?, themeColor: Int, alpha: Float) {
        (view as TextView).setTextColor(themeColor)
    }
}
