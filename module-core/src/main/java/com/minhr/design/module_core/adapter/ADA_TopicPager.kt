package com.minhr.design.module_core.adapter

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import androidx.cardview.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alibaba.android.arouter.launcher.ARouter
import com.minhr.design.common_base.config.arouter.ARouterConfig
import com.minhr.design.common_base.config.arouter.ARouterConstants
import com.minhr.design.common_base.utils.glide.GlideUtils
import com.minhr.design.module_core.R
import com.minhr.design.module_core.bean.TopicBean
import com.minhr.design.module_core.ui.common.ACT_CommonWebView
import com.minhr.design.module_core.widgets.cardview.ICardAdapter
import java.util.ArrayList

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 专题页面卡片适配器
 */
class ADA_TopicPager constructor(context: Context, datas: List<TopicBean>) : PagerAdapter(), ICardAdapter {

    private var mViewList = ArrayList<CardView?>()
    private var mDatas = ArrayList<TopicBean>()
    private var mBaseElevation: Float = 0.toFloat()
    private var mContext: Context? = null

    init {
        mContext = context
        mDatas = datas as ArrayList<TopicBean>
        mViewList.clear()
        for (i in datas.indices) {
            mViewList.add(null)
        }
    }

    fun notifyChanged(datas: List<TopicBean>) {
        mDatas.clear()
        mViewList.clear()
        for (i in datas.indices) {
            mViewList.add(null)
            mDatas.add(datas[i])
        }
        notifyDataSetChanged()
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
        mViewList[position] = null
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
                .inflate(R.layout.item_topic_card, container, false)
        container.addView(view)
        val cardView = view.findViewById<CardView>(R.id.cardView)

        //专题bean
        val bean = mDatas[position]
        // 专题点击：想去 H5 / 百度在 WebView 中易跳 App 深链导致「网页无法打开」，改为本地预览页
        cardView.setOnClickListener {
            ARouter.getInstance().build(ARouterConfig.ACT_WEBVIEW)
                .withString(ARouterConstants.WEB_TITLE, bean.name)
                .withString(ARouterConstants.WEB_URL, ACT_CommonWebView.LOCAL_PREVIEW_SCHEME)
                .withString(ARouterConstants.WEB_IMAGE, bean.image)
                .withString(ARouterConstants.WEB_DESC, "专题详情演示（原想去 H5 已下线）")
                .navigation()
        }

        val ivCard = view.findViewById<ImageView>(R.id.iv_card)
        // loadImage 会裁掉末尾字符再拼 .png，仅适配想去旧 CDN；兜底图用正常 URL 加载
        GlideUtils.loadNormalImage(bean.image, ivCard, 0)
        if (mBaseElevation == 0f) {
            mBaseElevation = cardView.cardElevation
        }
        cardView.maxCardElevation = mBaseElevation * getMaxElevationFactor()
        mViewList[position] = cardView
        return view
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj
    override fun getCount(): Int = mViewList.size
    override fun getBaseElevation(): Float = mBaseElevation

    override fun getCardViewAt(position: Int): CardView? =
        if (position in mViewList.indices) mViewList[position] else null

    override fun getMaxElevationFactor(): Int = 10
}