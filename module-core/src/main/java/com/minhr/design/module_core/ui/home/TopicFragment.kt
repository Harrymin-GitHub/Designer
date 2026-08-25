package com.minhr.design.module_core.ui.home

import android.os.Bundle
import com.minhr.design.common_base.BaseAppliction
import com.minhr.design.common_base.dagger.mvp.BaseFragment
import com.minhr.design.common_ui.view.MultipleStatusView
import com.minhr.design.module_core.R
import com.minhr.design.module_core.adapter.ADA_TopicPager
import com.minhr.design.module_core.bean.TopicBean
import com.minhr.design.module_core.bean.TopicDetailEntity
import com.minhr.design.module_core.dagger2.DaggerCoreComponent
import com.minhr.design.module_core.mvp.contract.TopicContract
import com.minhr.design.module_core.mvp.model.TopicModel
import com.minhr.design.module_core.mvp.presenter.TopicPresenter
import com.minhr.design.module_core.widgets.cardview.ShadowTransformer
import com.smart.novel.util.bindView
import com.will.weiyuekotlin.component.ApplicationComponent
import androidx.viewpager.widget.ViewPager
import java.util.ArrayList

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 专题
 */
class TopicFragment : BaseFragment<TopicPresenter, TopicModel>(), TopicContract.View {
    private val vp_card by bindView<ViewPager>(R.id.vp_card)
    private var mTitle: String? = null
    private val mDatas = ArrayList<TopicBean>()
    private var mCardAdapter: ADA_TopicPager? = null
    private var mCardShadowTransformer: ShadowTransformer? = null
    override fun getContentViewLayoutId(): Int = R.layout.fra_topic

    companion object {
        fun getInstance(title: String): TopicFragment {
            var fragment = TopicFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun onFirstUserVisible() {
    }

    override fun onFirstUserInvisible() {
    }

    override fun onUserVisible() {
    }

    override fun onUserInvisible() {
    }

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerCoreComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startFragmentEvents() {
        mPresenter?.getTopics("131")
        mPresenter?.getTopicDetail("5192")

        mCardAdapter = ADA_TopicPager(mContext, mDatas)
        mCardShadowTransformer = ShadowTransformer(vp_card, mCardAdapter!!)
        mCardShadowTransformer?.enableScaling(true)
        vp_card.adapter = mCardAdapter
        vp_card.setPageTransformer(false, mCardShadowTransformer)
        vp_card.offscreenPageLimit = 3
    }

    override fun getTopics(dataList: List<TopicBean>) {
        mCardAdapter?.notifyChanged(dataList)
        // ViewPager 完成布局后再开缩放，避免 CardView 尚未创建时 NPE
        vp_card.post {
            mCardShadowTransformer?.enableScaling(true)
        }
    }

    override fun getTopicDetail(dataBean: TopicDetailEntity) {
    }
}