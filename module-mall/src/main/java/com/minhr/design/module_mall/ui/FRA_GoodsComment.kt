package com.minhr.design.module_mall.ui

import android.widget.TextView
import com.minhr.design.common_base.BaseAppliction
import com.minhr.design.common_base.dagger.mvp.BaseFragment
import com.minhr.design.common_ui.view.MultipleStatusView
import com.minhr.design.module_mall.R
import com.minhr.design.module_mall.adapter.ADA_GoodsComment
import com.minhr.design.module_mall.adapter.ADA_GoodsRecommend
import com.minhr.design.module_mall.bean.CommentBean
import com.minhr.design.module_mall.bean.GoodsContentBean
import com.minhr.design.module_mall.bean.GoodsDesBean
import com.minhr.design.module_mall.bean.RevelentBean
import com.minhr.design.module_mall.dagger2.DaggerMallComponent
import com.minhr.design.module_mall.mvp.contract.GoodsContract
import com.minhr.design.module_mall.mvp.model.GoodsModel
import com.minhr.design.module_mall.mvp.presenter.GoodsPresenter
import com.minhr.design.common_ui.view.NoScrollGridView
import com.minhr.design.common_ui.view.NoScrollListView
import com.smart.novel.util.bindView
import com.will.weiyuekotlin.component.ApplicationComponent

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品详情-评价
 */
class FRA_GoodsComment : BaseFragment<GoodsPresenter, GoodsModel>(), GoodsContract.View {
    private val gv_recomment by bindView<NoScrollGridView>(R.id.gv_recomment)
    private val lv_comment by bindView<NoScrollListView>(R.id.lv_comment)
    private val tv_comment_num by bindView<TextView>(R.id.tv_comment_num)
    var mAdapter: ADA_GoodsRecommend? = null
    var mAdapterComment: ADA_GoodsComment? = null
    override fun getContentViewLayoutId(): Int = R.layout.fra_goods_comment

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
        DaggerMallComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startFragmentEvents() {
        mPresenter?.getGoodsCommentList((activity as ACT_GoodsDetail).productId.orEmpty(), 0)
        mPresenter?.getRevelentGoodsList((activity as ACT_GoodsDetail).productId.orEmpty())

        mAdapter = ADA_GoodsRecommend(mContext)
        gv_recomment.adapter = mAdapter

        mAdapterComment = ADA_GoodsComment(mContext)
        lv_comment.adapter = mAdapterComment
    }


    override fun getGoodsCommentList(dataList: List<CommentBean>) {
        tv_comment_num.text = "所有" + dataList.size + "条评论"
        mAdapterComment?.update(dataList, true)
    }

    override fun getRevelentGoodsList(dataBean: RevelentBean) {
        mAdapter?.update(dataBean?.revelentList, true)
    }

    override fun getGoodsContent(dataBean: GoodsContentBean) {
    }

    override fun getGoodsDescription(dataList: List<GoodsDesBean>) {
    }

}