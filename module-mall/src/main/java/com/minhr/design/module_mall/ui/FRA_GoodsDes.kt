package com.minhr.design.module_mall.ui

import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import com.minhr.design.common_base.dagger.mvp.BaseContract
import com.minhr.design.common_base.dagger.mvp.BaseFragment
import com.minhr.design.common_ui.view.MultipleStatusView
import com.minhr.design.module_mall.R
import com.minhr.design.module_mall.adapter.ADA_GoodsDes
import com.minhr.design.module_mall.bean.GoodsDesBean
import com.minhr.design.common_ui.view.VerticalBottomRecyclerView
import com.smart.novel.util.bindView
import com.will.weiyuekotlin.component.ApplicationComponent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *    author : Harrymin
 *    e-mail : minhongrui@126.com
 *    date   : 2026/8/25 7:38 PM
 *    desc   : 商品详情-商品描述
 */
class FRA_GoodsDes : BaseFragment<BaseContract.BasePresenter, BaseContract.BaseModel>() {
    private val lv_content by bindView<VerticalBottomRecyclerView>(R.id.lv_content)
    var mAdapter: ADA_GoodsDes? = null
    override fun getContentViewLayoutId(): Int = R.layout.fra_goods_des
    override fun isBindEventBus(isBind: Boolean): Boolean {
        return super.isBindEventBus(true)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveDataList(dataList: List<GoodsDesBean>) {
        Log.e("TAG", "onReceiveDataList=" + dataList.size)
        mAdapter?.update(dataList, true)
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
    }

    override fun startFragmentEvents() {
        mAdapter = ADA_GoodsDes(mContext)
        lv_content.layoutManager =
            LinearLayoutManager(mContext)
        lv_content.adapter = mAdapter

    }
}